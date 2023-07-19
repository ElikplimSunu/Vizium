package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.CourseActivity
import com.sunueric.prototype1.ui.ReaderActivity
import com.sunueric.prototype1.ui.theme.Prototype1Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8FAFB))
    ) {
        val context = LocalContext.current

        val textToSpeech = TextToSpeech(context, null)

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.White)


        ) {
            Text(
                text = "Overview",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ).copy(lineHeight = 32.sp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .combinedClickable(
                        onClick = {
                            Toast
                                .makeText(context, "Single Tap", Toast.LENGTH_SHORT)
                                .show()
                        },

                        onDoubleClick = {
                            Toast
                                .makeText(
                                    context,
                                    "Double Tap",
                                    Toast.LENGTH_SHORT
                                )
                                .show()

                            context.startActivity(
                                Intent(
                                    context,
                                    ReaderActivity::class.java
                                )
                            )
                        },
                        onLongClick = {
                            Toast
                                .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                                .show()

                            vibrateDevice(context)

                            textToSpeech.speak(
                                "Overview for English Language",
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                ""
                            )

                        }
                    )
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(5f), text = "English Language", style = TextStyle(
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

        Topics()
    }



}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Topics() {

    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFFF8FAFB)),
        verticalArrangement = Arrangement.spacedBy(20.dp),

        state = lazyListState
    ) {

        items(topics) { chunk ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                // This is to add a padding to the top of the first item
                val topicsSize = topics.size - 1
                when(chunk) {
                    topics[0] -> ListItem(context, textToSpeech, chunk, 20, activity = CourseActivity())
                    topics[topicsSize] -> ListItem(context, textToSpeech, chunk, 0, 20, activity = CourseActivity())
                    else -> ListItem(context, textToSpeech, chunk, activity = CourseActivity())
                }
            }
        }
    }
}


fun vibrateDevice(context: Context) {
    val vibrator = getSystemService(context, Vibrator::class.java)
    vibrator?.let {
        if (Build.VERSION.SDK_INT >= 26) {
            it.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            it.vibrate(100)
        }
    }
}

val topics = listOf(
    "Sentence Structure",
    "Parts of Speech",
    "Tenses",
    "Pronouns",
    "Verbs",
    "Nouns",
    "Adjectives",
    "Adverbs",
    "Prepositions"
)


@Preview(showBackground = true)
@Composable
fun CoursesScreenPreview() {
    Prototype1Theme {
        CourseScreen()
    }
}


