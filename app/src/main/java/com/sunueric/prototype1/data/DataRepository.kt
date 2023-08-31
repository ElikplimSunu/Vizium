package com.sunueric.prototype1.data

import Classes
import Courses
import Lesson
import QuizResult
import SubCourse
import Topics
import UserProfile
import UserRecentCourse


interface DataRepository {

    // For Classes:
    suspend fun fetchClassesFromFirestore(): List<Classes>
    suspend fun pushClassesToFirestore(classes: List<Classes>)

    // For Courses:
    suspend fun fetchCoursesForClass(classId: String): List<Courses>
    suspend fun pushCoursesToFirestore(courses: List<Courses>)
    suspend fun fetchAllCoursesFromFirestore(): List<Courses>

    // For SubCourses:
    suspend fun fetchSubCoursesForCourse(courseId: String): List<SubCourse>
    suspend fun pushSubCoursesToFirestore(subCourses: List<SubCourse>)
    suspend fun fetchAllSubCoursesFromFirestore(): List<SubCourse>

    // For Topics:
    suspend fun fetchTopicsForSubCourse(subCourseId: String): List<Topics>
    suspend fun pushTopicsToFirestore(topics: List<Topics>)
    suspend fun fetchAllTopicsFromFirestore(): List<Topics>

    // For "recent topics" (related to user context):
    suspend fun fetchRecentTopicsForUser(userId: String): List<Topics>
    suspend fun pushRecentTopicsForUser(userId: String, recentTopics: List<Topics>)

    // For Lessons:
    suspend fun fetchLessonsForTopic(topicId: String): List<Lesson>
    suspend fun pushLessonsToFirestore(lessons: List<Lesson>)
    suspend fun fetchAllLessonsFromFirestore(): List<Lesson>

    // User Profile:
    suspend fun fetchUserProfile(userId: String): UserProfile
    suspend fun updateUserProfile(userProfile: UserProfile): Boolean
    suspend fun createUserProfile(userProfile: UserProfile)

    // User Recent Courses:
    suspend fun fetchRecentCoursesForUser(userId: String): List<Courses>
    suspend fun pushRecentCourseForUser(userRecentCourse: UserRecentCourse)

    suspend fun fetchQuizResultsForUser(userId: String): List<QuizResult>
    suspend fun fetchQuizResultsForTopicAndUser(userId: String, topicId: String): List<QuizResult>
    suspend fun pushQuizResult(quizResult: QuizResult)
}
