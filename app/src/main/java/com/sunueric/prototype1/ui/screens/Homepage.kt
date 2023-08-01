package com.sunueric.prototype1.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
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
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.composables.GradeItem
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens

@Composable
fun HomepageScreen(navController: NavController, viewModel: SharedViewModel) {
    val mCheckedState = remember { mutableStateOf(false) }

    // Observe the isTalkBackEnabled LiveData using the observeAsState() composable
    val isTalkBackEnabled by viewModel.isTalkBackEnabled.observeAsState()


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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(70.dp)
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

                SwitchWithLabel(
                    label = "Accessibility",
                    isChecked = mCheckedState.value,
                    onCheckedChange = { mCheckedState.value = it }
                )
            }

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

        Grades(navController = navController, isTalkBackEnabled)
    }
}

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


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 8.dp) then semanticsModifier
        )
    }
}

@Composable
fun Grades(navController: NavController, isTalkBackEnabled: Boolean?) {
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
                when (grade) {
                    grades[0] -> GradeItem(
                        context,
                        textToSpeech,
                        grade,
                        20,
                        navController = navController,
                        route = Screens.Courses.route,
                        isTalkBackEnabled = isTalkBackEnabled
                    )

                    grades[gradesSize] -> GradeItem(
                        context,
                        textToSpeech,
                        grade,
                        0,
                        20,
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
        HomepageScreen(navController = rememberNavController(), viewModel = SharedViewModel())
    }
}
