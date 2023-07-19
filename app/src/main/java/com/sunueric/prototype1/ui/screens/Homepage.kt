package com.sunueric.prototype1.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.CourseActivity
import com.sunueric.prototype1.ui.composables.ListItem
import com.sunueric.prototype1.ui.theme.Prototype1Theme


@Composable
fun HomepageScreen() {


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
                    text = "Homepage",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ).copy(lineHeight = 32.sp)
                )

                Text(
                    text = "Choose your course and start learning",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF68769F)
                    ).copy(lineHeight = 21.sp)
                )
            }

            Courses()

        }
//    }


    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Courses() {

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

        items(subjects) { chunk ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                // This is to add a padding to the top of the first item
                val subjectsSize = subjects.size - 1
                when(chunk) {
                    subjects[0] -> ListItem(context, textToSpeech, chunk, 20, activity = CourseActivity())
                    subjects[subjectsSize] -> ListItem(context, textToSpeech, chunk, 0, 20, activity = CourseActivity())
                    else -> ListItem(context, textToSpeech, chunk, activity = CourseActivity())
                }
            }
        }
    }
}




val subjects = listOf(
    "English",
    "Mathematics",
    "Social Studies",
    "Integrated Science",
    "Religious and Moral Education",
    "French",
    "ICT",
    "Biology",
    "Chemistry",
    "Physics",
    "Economics",
)

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
fun HomepageScreenPreview() {
    Prototype1Theme {
        HomepageScreen()
    }
}
