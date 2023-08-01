package com.sunueric.prototype1.ui.composables

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sunueric.prototype1.data.Course
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.data.Topic
import com.sunueric.prototype1.ui.utils.Screens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicsScreen(navController: NavController, viewModel: SharedViewModel) {
    // Observe the isTalkBackEnabled LiveData using the observeAsState() composable
    val isTalkBackEnabled by viewModel.isTalkBackEnabled.observeAsState()

    // Observe the selectedCourse LiveData to get the selected course
    val selectedCourse by viewModel.selectedCourse.observeAsState()

    // Fetch the selected course and its topics
    val course = selectedCourse ?: return
    val topics = course.topics


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8FAFB))
    ) {

        val context = LocalContext.current

        val textToSpeech = TextToSpeech(context, null)

        Topics(
            topics = topics,
            navController = navController,
            isTalkBackEnabled = isTalkBackEnabled,
            selectedCourse,
            course,
            viewModel = viewModel
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Topics(
    topics: List<Topic>,
    navController: NavController,
    isTalkBackEnabled: Boolean?,
    selectedCourse: Course?,
    course: Course,
    viewModel: SharedViewModel
) {

    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)


    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFFF8FAFB)),
        verticalArrangement = Arrangement.spacedBy(20.dp),

        state = lazyListState
    ) {

        items(topics) { topic ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                // This is to add a padding to the top of the first item
                val lastTopic = topics.size - 1
                when (topic) {
                    topics[0] -> FirstTopicItem(
                        selectedCourse,
                        context,
                        textToSpeech,
                        paddingTop = 20,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route,
                        isTalkBackEnabled = isTalkBackEnabled,
                        course,
                        viewModel
                    )

                    topics[lastTopic] -> TopicItem(
                        context,
                        textToSpeech,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route,
                        isTalkBackEnabled = isTalkBackEnabled,
                        viewModel = viewModel
                    )

                    else -> TopicItem(
                        context,
                        textToSpeech,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route,
                        isTalkBackEnabled = isTalkBackEnabled,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


val subjects = listOf(
    "English",
    "Integrated Science",
    "Mathematics",
    "Social Studies",
    "Religious and Moral Education",
    "ICT",
    "French"
)