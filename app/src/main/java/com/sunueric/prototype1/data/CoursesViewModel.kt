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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _courses = MutableLiveData<List<Courses>>()
    val courses: LiveData<List<Courses>> get() = _courses

    private val _subCourses = MutableLiveData<List<SubCourse>>()
    val subCourses: LiveData<List<SubCourse>> get() = _subCourses

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _classes = MutableLiveData<List<Classes>>() // Adjusted to the new Class type
    val classes: LiveData<List<Classes>> get() = _classes

    private val _selectedClassCourses = MutableLiveData<List<Courses>>() // Courses for the selected class
    val selectedClassCourses: LiveData<List<Courses>> get() = _selectedClassCourses

    private val _topics = MutableLiveData<List<Topics>>()
    val topics: LiveData<List<Topics>> get() = _topics

    private val _recentTopicsForUser = MutableLiveData<List<Topics>>()
    val recentTopicsForUser: LiveData<List<Topics>> get() = _recentTopicsForUser

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> get() = _userProfile

    private val _recentCoursesForUser = MutableLiveData<List<Courses>>()
    val recentCoursesForUser: LiveData<List<Courses>> get() = _recentCoursesForUser

    private val _quizResultsForUser = MutableLiveData<List<QuizResult>>()
    val quizResultsForUser: LiveData<List<QuizResult>> get() = _quizResultsForUser

    private val _lessons = MutableLiveData<List<Lesson>>()
    val lessons: LiveData<List<Lesson>> get() = _lessons


    init {
        fetchAllClasses()  // On initialization, fetch only the classes
    }

    fun sendDataToFirestoreWithClasses(
        classes: List<Classes>,
        courses: List<Courses>,
        subCourses: List<SubCourse>,
        topics: List<Topics>,
        lessons: List<Lesson>
    ) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushClassesToFirestore(classes)
                Log.d("Firestore", "Classes pushed successfully")

                dataRepository.pushCoursesToFirestore(courses)
                Log.d("Firestore", "Courses pushed successfully")

                dataRepository.pushSubCoursesToFirestore(subCourses)
                Log.d("Firestore", "SubCourses pushed successfully")

                dataRepository.pushTopicsToFirestore(topics)
                Log.d("Firestore", "Topics pushed successfully")

                dataRepository.pushLessonsToFirestore(lessons)
                Log.d("Firestore", "Lessons pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing data: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchAllClasses() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedClasses = dataRepository.fetchClassesFromFirestore()
                _classes.value = fetchedClasses
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushClassesToFirestore(classes: List<Classes>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushClassesToFirestore(classes)
                Log.d("Firestore", "Classes pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchCoursesForSelectedClass(classId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedCourses = dataRepository.fetchCoursesForClass(classId)
                _selectedClassCourses.value = fetchedCourses
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushCoursesForSelectedClass(courses: List<Courses>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushCoursesToFirestore(courses)
                Log.d("Firestore", "Courses pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchSubCoursesForSelectedClass(courseId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedSubCourses = dataRepository.fetchSubCoursesForCourse(courseId)
                _subCourses.value = fetchedSubCourses
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error fetching subCourses: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushSubCoursesForSelectedClass(subCourses: List<SubCourse>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushSubCoursesToFirestore(subCourses)
                Log.d("Firestore", "SubCourses pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing subCourses: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchTopicsForSubCourse(subCourseId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedTopics = dataRepository.fetchTopicsForSubCourse(subCourseId)
                _topics.value = fetchedTopics
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushTopicsForSubCourse(topics: List<Topics>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushTopicsToFirestore(topics)
                Log.d("Firestore", "Topics pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchRecentTopicsForUser(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val recentTopics = dataRepository.fetchRecentTopicsForUser(userId)
                _recentTopicsForUser.value = recentTopics
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushRecentTopicsForUser(userId: String, recentTopics: List<Topics>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushRecentTopicsForUser(userId, recentTopics)
                Log.d("Firestore", "Recent topics pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing recent topics: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchLessonsForTopic(lessons: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedLessons = dataRepository.fetchLessonsForTopic(lessons)
                _lessons.value = fetchedLessons
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error fetching lessons: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushLessonsForTopic(lessons: List<Lesson>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushLessonsToFirestore(lessons)
                Log.d("Firestore", "Lessons pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing lessons: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchUserProfile(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val userProfileData = dataRepository.fetchUserProfile(userId)
                _userProfile.value = userProfileData
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.updateUserProfile(userProfile)
                Log.d("Firestore", "User profile pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing user profile: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchRecentCoursesForUser(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val recentCourses = dataRepository.fetchRecentCoursesForUser(userId)
                _recentCoursesForUser.value = recentCourses
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushRecentCourseForUser(userRecentCourse: UserRecentCourse) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushRecentCourseForUser(userRecentCourse)
                Log.d("Firestore", "Recent course pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing recent course: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchQuizResultsForUser(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val quizResults = dataRepository.fetchQuizResultsForUser(userId)
                _quizResultsForUser.value = quizResults
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun pushQuizResult(quizResult: QuizResult) {
        viewModelScope.launch {
            _loading.value = true
            try {
                dataRepository.pushQuizResult(quizResult)
                Log.d("Firestore", "Quiz result pushed successfully")
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("Firestore", "Error pushing quiz result: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}