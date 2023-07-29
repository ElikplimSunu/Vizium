package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.sunueric.prototype1.ui.screens.subjects
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens


@Composable
fun CoursesScreen (navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8FAFB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 25.dp)
        ) {
            Text(
                text = "Subjects or Courses",
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ).copy(lineHeight = 32.sp)
            )

            Text(
                text = "Select a subject and start learning",
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF68769F)
                ).copy(lineHeight = 21.sp)
            )
        }

        Courses(navController = navController)
    }
}


@Composable
fun Courses(navController: NavController) {

    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFFF8FAFB))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),

        state = lazyListState
    ) {

        items(subjects) { subject ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                // This is to add a padding to the top of the first item
                val subjectsSize = subjects.size - 1
                when(subject) {
                    subjects[0] -> ListItem(context, textToSpeech, subject, 20, navController = navController, route = Screens.Topics.route)
                    subjects[subjectsSize] -> ListItem(context, textToSpeech, subject, 0, 20, navController =  navController, route = Screens.Topics.route)
                    else -> ListItem(context, textToSpeech, subject, navController = navController, route = Screens.Topics.route)
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
        //CourseScreen()
    }
}


