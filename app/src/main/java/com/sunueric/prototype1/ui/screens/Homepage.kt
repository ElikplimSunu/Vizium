package com.sunueric.prototype1.ui.screens

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.ui.composables.ListItem
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens


@Composable
fun HomepageScreen(navController: NavController) {

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
                        fontFamily = dmSans,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ).copy(lineHeight = 32.sp)
                )

                Text(
                    text = "Choose your grade and start learning",
                    style = TextStyle(
                        fontFamily = dmSans,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF68769F)
                    ).copy(lineHeight = 21.sp)
                )
            }

            Grades(navController = navController)
        }
}

@Composable
fun Grades(navController: NavController) {

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

        items(grades) { grade ->
            Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                // This is to add a padding to the top of the first item
                val gradesSize = grades.size - 1
                when(grade) {
                    grades[0] -> ListItem(context, textToSpeech, grade, 20, navController = navController, route = Screens.Courses.route)
                    grades[gradesSize] -> ListItem(context, textToSpeech, grade, 0, 20, navController =  navController, route = Screens.Courses.route)
                    else -> ListItem(context, textToSpeech, grade, navController = navController, route = Screens.Courses.route)
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

val grades = listOf(
    "Primary 1",
    "Primary 2",
    "Primary 3",
    "Primary 4",
    "Primary 5",
    "Primary 6"
)


@Preview(showBackground = true)
@Composable
fun HomepageScreenPreview() {
    Prototype1Theme {
        HomepageScreen(navController = rememberNavController())
    }
}
