package com.sunueric.prototype1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunueric.prototype1.ui.utils.extractingTopicsFromPdf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Course(
    val name: String,
    val pdfFilePath: String, // Path to the PDF file for the course
    val topics: List<Topic> = emptyList()
)
data class Topic(val name: String, val body: String)

class SharedViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    private val _selectedCourse = MutableLiveData<Course>()
    val selectedCourse: LiveData<Course> get() = _selectedCourse

    private val _topics = MutableLiveData<List<Topic>>()
    val topics: LiveData<List<Topic>> get() = _topics

    private val _selectedTopicBody = MutableLiveData<String>()
    val selectedTopicBody: LiveData<String> get() = _selectedTopicBody

    init {
        // Fetch courses from the data source and set them in the ViewModel
        viewModelScope.launch(Dispatchers.IO) {
            val coursesList = listOf(
                Course("English Language", "assets/english_language.pdf"),
                Course("Mathematics", "path/to/mathematics.pdf"),
                // Add other courses here with their respective PDF file paths
            )

            val coursesWithTopics = coursesList.map { course ->
                val extractedTopics = extractingTopicsFromPdf(course.pdfFilePath)
                course.copy(topics = extractedTopics)
            }

            _courses.postValue(coursesWithTopics)
        }
    }
    fun setCoursesAndTopics(courses: List<Course>, topics: List<Topic>) {
        _courses.value = courses
        _topics.value = topics
    }

    fun setSelectedCourse(course: Course) {
        _selectedCourse.value = course
        // Fetch topics for the selected course (You can modify this based on your data source)
        _topics.value = course.topics
    }

    fun setSelectedTopicBody(body: String) {
        _selectedTopicBody.value = body
    }
}

class NavigationViewModel : ViewModel() {
    private val _selectedCourse = MutableLiveData<Course>()
    val selectedCourse: LiveData<Course> get() = _selectedCourse

    private val _topics = MutableLiveData<List<Topic>>()
    val topics: LiveData<List<Topic>> get() = _topics

    // Function to set the selected course and fetch its topics
    fun setSelectedCourse(course: Course) {
        _selectedCourse.value = course
        // Fetch topics for the selected course (You can modify this based on your data source)
        _topics.value = course.topics
    }
}

val courses = listOf(
    Course("English Language", "assets/english_language.pdf"),
    //Course("Mathematics", "path/to/mathematics.pdf"),
    // Add other courses here
)

