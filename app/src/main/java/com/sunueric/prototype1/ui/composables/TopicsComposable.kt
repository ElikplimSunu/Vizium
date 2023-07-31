package com.sunueric.prototype1.ui.composables

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.data.Topic
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicsScreen(navController: NavController,  viewModel: SharedViewModel){
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

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.White)
        ) {
            Column (modifier = Modifier
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(context, "Single Tap", Toast.LENGTH_SHORT)
                            .show()

                        textToSpeech.speak(
                            "Overview for English Language",
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            ""
                        )
                    },

                    onDoubleClick = {
                        Toast
                            .makeText(
                                context,
                                "Double Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()

                        vibrateDevice(context)

                        // Launch the new screen
                        navController.navigate(Screens.Reader.route)
                    },
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                            .show()

                    }
                )
                .padding(20.dp)) {
                Text(
                    text = topics[0].name.lowercase().capitalize(Locale.ROOT),
                    style = TextStyle(
                        fontFamily = dmSans,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ).copy(lineHeight = 32.sp)
                )

                Spacer(modifier = Modifier.height(19.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    selectedCourse?.let{course ->
                        Text(
                            modifier = Modifier.weight(5f),
                            text = course.name,
                            style = TextStyle(
                                fontFamily = dmSans,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B2559)
                            ).copy(lineHeight = 23.sp)
                        )
                    }
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = "Play overview"
                    )
                }
            }
        }

        Topics(topics = topics, navController = navController)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Topics(topics: List<Topic>, navController: NavController) {

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
                    topics[0] -> TopicItem(
                        context,
                        textToSpeech,
                        paddingTop = 20,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route
                    )

                    topics[lastTopic] -> TopicItem(
                        context,
                        textToSpeech,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route
                    )

                    else -> TopicItem(
                        context,
                        textToSpeech,
                        topic = topic,
                        navController = navController,
                        route = Screens.Reader.route
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