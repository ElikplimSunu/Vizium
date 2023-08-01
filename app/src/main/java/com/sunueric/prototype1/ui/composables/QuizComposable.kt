package com.sunueric.prototype1.ui.composables

//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.RadioButtonDefaults
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.sunueric.prototype1.QuizViewModel
//import com.sunueric.prototype1.ui.CourseActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans

@Composable
fun QuizScreen() {
//    val viewModel = viewModel<QuizViewModel>()
//    val quizQuestions by viewModel.quizQuestions.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8FAFB))
            .padding(20.dp)
    ) {
        Text(
            text = "English Language",
            style = TextStyle(
                fontFamily = dmSans,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF68769F),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Question ",
//                    "${viewModel.currentQuestionIndex + 1}",
            style = TextStyle(
                fontFamily = dmSans,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B2559),
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(213.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFDFE4F3),
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .padding(20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "Question body",
//                quizQuestions.getOrNull(viewModel.currentQuestionIndex) ?: "",
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B2559),
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .background(color = Color(0xFFF8FAFB)),
            verticalArrangement = Arrangement.spacedBy(20.dp),

            state = lazyListState
        ) {

            items(optionsForQuiz) { options ->
                Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                    val optionsSize = optionsForQuiz.size - 1
                    when (options) {
                        optionsForQuiz[0] -> QuizOption(option = options, paddingTop = 20)
//                            , onClick = { viewModel.setUserAnswer(options) })
                        optionsForQuiz[optionsSize] -> QuizOption(
                            option = options,
                            paddingBottom = 16
                        )

                        else -> QuizOption(option = options)
                    }
                }
            }
        }
    }

}

val optionsForQuiz = listOf(
    "(A) First option",
    "(B) Second option",
    "(C) Third option",
    "(D) Fourth option"
)


@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    Prototype1Theme {
        QuizScreen()
    }
}
