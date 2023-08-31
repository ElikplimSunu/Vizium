package com.sunueric.prototype1.ui.screens

import Classes
import Courses
import Lesson
import QuizResult
import SubCourse
import Topics
import UserActivity
import UserProfile
import UserRecentCourse
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.data.CoursesViewModel
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.composables.GradeItem
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens

@Composable
fun HomepageScreen(
    navController: NavController,
    viewModel: SharedViewModel,
    coursesViewModel: CoursesViewModel?
) {
    val mCheckedState = remember { mutableStateOf(false) }

    // Observe the isTalkBackEnabled LiveData using the observeAsState() composable
    val isTalkBackEnabled by viewModel.isTalkBackEnabled.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 25.dp)
        ) {
//            Button(onClick = { pushSampleDataToFirestore(viewModel = coursesViewModel!!) }) {
                Text(
                    text = "What Class Are You In?",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
//            }

            Text(
                text = "Choose your grade and start learning",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = dmSans,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF595959),
                )
            )
        }

        Grades(navController = navController, isTalkBackEnabled)

    }
}

/** This is a function to push sample data to Firestore.
 * it is a test function.
 * @param viewModel: CoursesViewModel to push data to Firestore.
 * */
private fun pushSampleDataToFirestore(viewModel: CoursesViewModel) {

    // Classes Data
    val sampleClasses = listOf(
        Classes("class_1", "Grade 5"),
        Classes("class_2", "Grade 6"),
        Classes("class_3", "Grade 7")
    )

    // Courses Data
    val sampleCourses = listOf(
        Courses("course_1", "class_1", "Physics", R.drawable.baseline_menu_book_24),
        Courses("course_2", "class_2", "Mathematics", R.drawable.baseline_calculate_24),
        Courses("course_3", "class_3", "Chemistry", R.drawable.baseline_science_24)
    )

    // SubCourses Data
    val sampleSubCourses = listOf(
        SubCourse("subcourse_1", "Mechanics", "course_1"),
        SubCourse("subcourse_2", "Algebra", "course_2"),
        SubCourse("subcourse_3", "Organic Chemistry", "course_3")
    )

    // Topics Data
    val sampleTopics = listOf(
        Topics("topic_1", "Kinematics", "subcourse_1"),
        Topics("topic_2", "Linear Equations", "subcourse_2"),
        Topics("topic_3", "Hydrocarbons", "subcourse_3")
    )

    // Lessons Data
    val sampleLessons = listOf(
        Lesson("lesson_1", "Introduction to Kinematics", "Lorem Ipsum Kinematics", listOf(R.drawable.google_logo), "topic_1"),
        Lesson("lesson_2", "Understanding Linear Equations", "Lorem Ipsum Linear Equations", listOf(R.drawable.google_logo), "topic_2"),
        Lesson("lesson_3", "Basics of Hydrocarbons", "Lorem Ipsum Hydrocarbons", listOf(R.drawable.google_logo), "topic_3")
    )

    // User Profiles
    val sampleUserProfiles = listOf(
        UserProfile("user_1", "Alice", "alice@example.com", "url_to_alice_profile_image"),
        UserProfile("user_2", "Bob", "bob@example.com", "url_to_bob_profile_image"),
        UserProfile("user_3", "Charlie", "charlie@example.com", "url_to_charlie_profile_image")
    )

    // User Activity
    val currentTime = System.currentTimeMillis()
    val sampleUserActivities = listOf(
        UserActivity("user_1", "topic_1", currentTime - 3600000),
        UserActivity("user_2", "topic_2", currentTime - 7200000),
        UserActivity("user_3", "topic_3", currentTime)
    )

    // User Recent Courses
    val sampleUserRecentCourses = listOf(
        UserRecentCourse("user_1", "course_1", currentTime - 10000),
        UserRecentCourse("user_2", "course_2", currentTime - 20000),
        UserRecentCourse("user_3", "course_3", currentTime)
    )

    // Quiz Results
    val sampleQuizResults = listOf(
        QuizResult("user_1", "topic_1", 8, 10, currentTime - 50000),
        QuizResult("user_2", "topic_2", 6, 10, currentTime - 100000),
        QuizResult("user_3", "topic_3", 9, 10, currentTime)
    )

    // Assuming your ViewModel has functions to send each data type to Firestore.
    viewModel.pushClassesToFirestore(sampleClasses)
    viewModel.pushCoursesForSelectedClass(sampleCourses)
    viewModel.pushSubCoursesForSelectedClass(sampleSubCourses)
    viewModel.pushTopicsForSubCourse(sampleTopics)
    viewModel.pushLessonsForTopic(sampleLessons)

    viewModel.pushRecentCourseForUser(sampleUserRecentCourses[0])
    viewModel.pushQuizResult(sampleQuizResults[0])
}

//Unused switch with label. DO NOT DELETE IT YET OH
@Composable
fun SwitchWithLabel(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val switchState by rememberUpdatedState(newValue = isChecked)

    val semanticsModifier = Modifier.semantics {
        // Set the contentDescription to the label when the switch is checked and unchecked
        contentDescription = label + if (switchState) ", checked" else ", unchecked"

        // Set the role of the switch to a checkbox
        role = Role.Switch
    }

}

@Composable
fun Grades(navController: NavController, isTalkBackEnabled: Boolean?) {
    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),

        state = lazyListState
    ) {

        items(grades) { grade ->
            Column(modifier = Modifier.background(color = Color.White)) {
                // This is to add a padding to the top of the first item
                val gradesSize = grades.size - 1
                when (grade) {
                    grades[0] -> GradeItem(
                        context,
                        textToSpeech,
                        grade,
                        navController = navController,
                        route = Screens.Courses.route,
                        isTalkBackEnabled = isTalkBackEnabled
                    )

                    grades[gradesSize] -> GradeItem(
                        context,
                        textToSpeech,
                        grade,
                        navController = navController,
                        route = Screens.Courses.route,
                        isTalkBackEnabled = isTalkBackEnabled
                    )

                    else -> GradeItem(
                        context,
                        textToSpeech,
                        grade,
                        navController = navController,
                        route = Screens.Courses.route,
                        isTalkBackEnabled = isTalkBackEnabled
                    )
                }
            }
        }
    }
}

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
        HomepageScreen(navController = rememberNavController(), viewModel = SharedViewModel(), null)
    }
}
