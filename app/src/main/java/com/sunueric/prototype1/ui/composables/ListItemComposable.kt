package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.data.Course
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.data.Topic
import com.sunueric.prototype1.ui.theme.dmSans
import java.util.Locale

/** This function is used to create a list item.
 *  @param context is the context of the activity.
 *  @param textToSpeech is the text to speech object.
 *  @param chunk is the text to be spoken.
 **/

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun GradeItem(
    context: Context,
    textToSpeech: TextToSpeech,
    chunk: String,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    navController: NavController,
    route: String,
    isTalkBackEnabled: Boolean?
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = paddingTop.dp, bottom = paddingBottom.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                chunk,
                                Toast.LENGTH_SHORT
                            )
                            .show()


                        if (isTalkBackEnabled == true) {
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            navController.navigate(route)
                        } else {
                            textToSpeech.speak(
                                chunk,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                ""
                            )
                        }

                    },
                    onDoubleClick = {
                        Toast
                            .makeText(
                                context,
                                "Double Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        if (isTalkBackEnabled == false) {
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            navController.navigate(route)
                        }
                    },
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(5f), text = chunk, style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B2559)
                ).copy(lineHeight = 23.sp)
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play course"
            )
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun CourseItem(
    context: Context,
    textToSpeech: TextToSpeech,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    navController: NavController,
    route: String,
    viewModel: SharedViewModel,
    course: Course,
    isTalkBackEnabled: Boolean?
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = paddingTop.dp, bottom = paddingBottom.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                course.name,
                                Toast.LENGTH_SHORT
                            )
                            .show()

                        if (isTalkBackEnabled == true) {
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            viewModel.setSelectedCourse(course)

                            navController.navigate(route)
                        } else {
                            textToSpeech.speak(
                                course.name,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                ""
                            )
                        }

                    },
                    onDoubleClick = {
                        Toast
                            .makeText(
                                context,
                                "Double Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()

                        if (isTalkBackEnabled == false) {
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            viewModel.setSelectedCourse(course)

                            navController.navigate(route)
                        }

                    },
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(5f), text = course.name, style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B2559)
                ).copy(lineHeight = 23.sp)
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play course"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstTopicItem(
    selectedCourse: Course?,
    context: Context, textToSpeech: TextToSpeech,
    paddingTop: Int, topic: Topic,
    navController: NavController,
    route: String,
    isTalkBackEnabled: Boolean?,
    course: Course,
    viewModel: SharedViewModel
) {
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
        Column(modifier = Modifier
            .background(color = Color.White)
            .combinedClickable(
                onClick = {
                    Toast
                        .makeText(context, "Single Tap", Toast.LENGTH_SHORT)
                        .show()

                    if (isTalkBackEnabled == true) {
                        ContextCompat
                            .getSystemService(
                                context,
                                Vibrator::class.java
                            )
                            ?.vibrate(100)

                        vibrateDevice(context)

                        // Launch the new screen
                        viewModel.setSelectedTopic(topic)
                        navController.navigate(route)
                    } else {
                        textToSpeech.speak(
                            "Overview for ${course.name}",
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            ""
                        )
                    }
                },

                onDoubleClick = {
                    Toast
                        .makeText(
                            context,
                            "Double Tap",
                            Toast.LENGTH_SHORT
                        )
                        .show()

                    if (isTalkBackEnabled == false) {
                        ContextCompat
                            .getSystemService(
                                context,
                                Vibrator::class.java
                            )
                            ?.vibrate(100)

                        vibrateDevice(context)

                        // Launch the new screen
                        // Launch the new screen
                        viewModel.setSelectedTopic(topic)
                        navController.navigate(route)
                    }
                },
                onLongClick = {
                    Toast
                        .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                        .show()

                }
            )
            .padding(20.dp)) {

            Text(
                text = topic.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ).copy(lineHeight = 32.sp)
            )

            //Spacer(modifier = Modifier.height(19.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 40.dp)
                    .align(Alignment.End),
                text = "for",
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                selectedCourse?.let { course ->
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

                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = "Play overview"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicItem(
    context: Context,
    textToSpeech: TextToSpeech,
    topic: Topic,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    navController: NavController,
    route: String,
    isTalkBackEnabled: Boolean?,
    viewModel: SharedViewModel
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = paddingTop.dp, bottom = paddingBottom.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                topic.name,
                                Toast.LENGTH_SHORT
                            )
                            .show()


                        if (isTalkBackEnabled == true) {
                            navController.navigate(route)
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            viewModel.setSelectedTopic(topic)
                            navController.navigate(route)
                        } else {
                            textToSpeech.speak(
                                topic.name,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                ""
                            )
                        }

                    },
                    onDoubleClick = {
                        Toast
                            .makeText(
                                context,
                                "Double Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()

                        if (isTalkBackEnabled == false) {
                            ContextCompat
                                .getSystemService(
                                    context,
                                    Vibrator::class.java
                                )
                                ?.vibrate(100)

                            viewModel.setSelectedTopic(topic)
                            navController.navigate(route)
                        }

                    },
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(5f),
                text = topic.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B2559)
                ).copy(lineHeight = 23.sp)
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play topic"
            )
        }
    }
}