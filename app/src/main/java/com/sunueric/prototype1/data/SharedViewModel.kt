package com.sunueric.prototype1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunueric.prototype1.ui.utils.extractingTopicsFromPdf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class Course(
    val name: String,
    val pdfFilePath: String, // Path to the PDF file for the course
    val topics: List<Topic> = emptyList()
)
data class Topic( val name: String, val body: String)

class SharedViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    private val _selectedCourse = MutableLiveData<Course>()
    val selectedCourse: LiveData<Course> get() = _selectedCourse

    private val _topics = MutableLiveData<List<Topic>>()
    val topics: LiveData<List<Topic>> get() = _topics

    private val _isTalkBackEnabled = MutableLiveData<Boolean>()
    val isTalkBackEnabled: LiveData<Boolean> get() = _isTalkBackEnabled


    private val _selectedTopicBody = MutableLiveData<String>()
    val selectedTopicBody: LiveData<String> get() = _selectedTopicBody

    private val _selectedTopic = MutableLiveData<Topic>()
    val selectedTopic: LiveData<Topic> get() = _selectedTopic

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading.asStateFlow()



    init {
        // Set the initial value for isLoading
        // This is just to simulate a delay in fetching data from the data source
        // This is necessary for the splash screen
        viewModelScope.launch {
            delay(2500)
            _isLoading.value = false
        }

        // Set the initial value for talkBackEnabled
        // This is for the accessibility switch in the settings screen
        _isTalkBackEnabled.value = false

        // Fetch courses from the data source and set them in the ViewModel
        viewModelScope.launch(Dispatchers.IO) {
            val coursesList = listOf(
                Course("English Language", "assets/english_language.pdf"),
                //Course("Mathematics", "path/to/mathematics.pdf"),
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
        _topics.value = course.topics
    }

    fun setTalkBackEnabled(isEnabled: Boolean) {
        _isTalkBackEnabled.value = isEnabled
    }

    fun setSelectedTopic(topic: Topic) {
        _selectedTopic.value = topic
        _selectedTopicBody.value = topic.body
    }
}

