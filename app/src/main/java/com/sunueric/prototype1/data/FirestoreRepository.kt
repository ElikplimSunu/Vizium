package com.sunueric.prototype1.data

import Classes
import Courses
import Lesson
import QuizResult
import SubCourse
import Topics
import UserProfile
import UserRecentCourse
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sunueric.prototype1.data.util.await


import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : DataRepository {
    // For Classes:
    override suspend fun fetchClassesFromFirestore(): List<Classes> {
        return try {
            val result = firestore.collection("classes").get().await()
            result.documents.mapNotNull { it.toObject(Classes::class.java) }
        } catch (e: Exception) {
            // You can log the error for debugging:
            Log.e("FirestoreRepository", "Error fetching classes: ${e.message}")
            emptyList() // Return an empty list to prevent any crashes in case of an error.
        }
    }

    override suspend fun fetchCoursesForClass(classId: String): List<Courses> {
        val result = firestore.collection("classes").document(classId).collection("courses").get().await()
        return result.documents.mapNotNull { it.toObject(Courses::class.java) }
    }

    override suspend fun pushClassesToFirestore(allClasses: List<Classes>) {
        val classesCollection = firestore.collection("classes")
        for (classes in allClasses) {
            classesCollection.document().set(classes).await()
        }
    }

    // For Courses:
    override suspend fun pushCoursesToFirestore(courses: List<Courses>) {
        val coursesCollection = firestore.collection("courses")
        for (course in courses) {
            coursesCollection.document().set(course).await()
        }
    }

    override suspend fun fetchAllCoursesFromFirestore(): List<Courses> {
        val result = firestore.collection("courses").get().await()
        return result.documents.mapNotNull { it.toObject(Courses::class.java) }
    }

    override suspend fun fetchSubCoursesForCourse(courseId: String): List<SubCourse> {
        val result = firestore.collection("courses").document(courseId).collection("subCourses").get().await()
        return result.documents.mapNotNull { it.toObject(SubCourse::class.java) }
    }

    // For SubCourses:
    override suspend fun pushSubCoursesToFirestore(subCourses: List<SubCourse>) {
        val subCoursesCollection = firestore.collection("subCourses")
        for (subCourse in subCourses) {
            subCoursesCollection.document().set(subCourse).await()
        }
    }

    override suspend fun fetchAllSubCoursesFromFirestore(): List<SubCourse> {
        val result = firestore.collection("subCourses").get().await()
        return result.documents.mapNotNull { it.toObject(SubCourse::class.java) }
    }

    override suspend fun fetchTopicsForSubCourse(subCourseId: String): List<Topics> {
        val result = firestore.collection("subCourses").document(subCourseId).collection("topics").get().await()
        return result.documents.mapNotNull { it.toObject(Topics::class.java) }
    }

    // For Topics:
    override suspend fun pushTopicsToFirestore(topics: List<Topics>) {
        val topicsCollection = firestore.collection("topics")
        for (topic in topics) {
            topicsCollection.document().set(topic).await()
        }
    }

    override suspend fun fetchAllTopicsFromFirestore(): List<Topics> {
        val result = firestore.collection("topics").get().await()
        return result.documents.mapNotNull { it.toObject(Topics::class.java) }
    }

    override suspend fun fetchRecentTopicsForUser(userId: String): List<Topics> {
        return try {
            val result = firestore.collection("users").document(userId).collection("recentTopics").get().await()
            result.documents.mapNotNull { it.toObject(Topics::class.java) }
        } catch (e: Exception) {
            Log.e("FirestoreRepository", "Error fetching recent topics for user: ${e.message}")
            emptyList() // Return an empty list in case of an error.
        }
    }

    override suspend fun pushRecentTopicsForUser(userId: String, recentTopics: List<Topics>) {
        try {
            val userRecentTopicsCollection = firestore.collection("users").document(userId).collection("recentTopics")
            for (topic in recentTopics) {
                userRecentTopicsCollection.add(topic).await()
            }
        } catch (e: Exception) {
            Log.e("FirestoreRepository", "Error pushing recent topics for user: ${e.message}")
        }
    }

    override suspend fun fetchLessonsForTopic(topicId: String): List<Lesson> {
        val result = firestore.collection("topics").document(topicId).collection("lessons").get().await()
        return result.documents.mapNotNull { it.toObject(Lesson::class.java) }
    }

    // For Lessons:
    override suspend fun pushLessonsToFirestore(lessons: List<Lesson>) {
        val lessonsCollection = firestore.collection("lessons")
        for (lesson in lessons) {
            lessonsCollection.document().set(lesson).await()
        }
    }

    override suspend fun fetchAllLessonsFromFirestore(): List<Lesson> {
        val result = firestore.collection("lessons").get().await()
        return result.documents.mapNotNull { it.toObject(Lesson::class.java) }
    }

    // User Profile:
    override suspend fun fetchUserProfile(userId: String): UserProfile {
        val result = firestore.collection("userProfiles").document(userId).get().await()
        return result.toObject(UserProfile::class.java) ?: throw Exception("User profile not found")
    }

    override suspend fun updateUserProfile(userProfile: UserProfile): Boolean {
        return try {
            firestore.collection("userProfiles").document(userProfile.userId).set(userProfile).await()
            true
        } catch (e: Exception) {
            Log.e("FirestoreRepository", "Error updating user profile: ${e.message}")
            false
        }
    }

    override suspend fun createUserProfile(userProfile: UserProfile) {
        firestore.collection("userProfiles").document(userProfile.userId).set(userProfile).await()
    }

    // User Recent Courses:
    override suspend fun fetchRecentCoursesForUser(userId: String): List<Courses> {
        val result = firestore.collection("userRecentCourses").whereEqualTo("userId", userId).orderBy("timestamp").get().await()
        return result.documents.mapNotNull { it.toObject(Courses::class.java) }
    }

    override suspend fun pushRecentCourseForUser(userRecentCourse: UserRecentCourse) {
        firestore.collection("userRecentCourses").document().set(userRecentCourse).await()
    }

    // Quiz Results:
    override suspend fun fetchQuizResultsForUser(userId: String): List<QuizResult> {
        val result = firestore.collection("quizResults").whereEqualTo("userId", userId).orderBy("timestamp").get().await()
        return result.documents.mapNotNull { it.toObject(QuizResult::class.java) }
    }

    override suspend fun fetchQuizResultsForTopicAndUser(userId: String, topicId: String): List<QuizResult> {
        val result = firestore.collection("quizResults").whereEqualTo("userId", userId).whereEqualTo("topicId", topicId).orderBy("timestamp").get().await()
        return result.documents.mapNotNull { it.toObject(QuizResult::class.java) }
    }

    override suspend fun pushQuizResult(quizResult: QuizResult) {
        firestore.collection("quizResults").document().set(quizResult).await()
    }
}