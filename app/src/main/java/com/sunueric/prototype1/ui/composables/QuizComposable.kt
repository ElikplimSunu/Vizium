package com.sunueric.prototype1.ui.composables

//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.RadioButtonDefaults
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.sunueric.prototype1.QuizViewModel
//import com.sunueric.prototype1.ui.CourseActivity
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

const val openAIEndpoint = "https://api.openai.com/v1/completions"
const val apiKey =
    "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" // Replace with your API key

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

@OptIn(InternalAPI::class)
fun getOpenAIResponse(
    prompt: String,
    onResponse: (String) -> Unit,
    onError: (Throwable) -> Unit,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch(Dispatchers.IO) {
        try {
            val response: HttpResponse = httpClient.post(openAIEndpoint) {
                header("Content-Type", "application/json")
                header("Authorization", "Bearer $apiKey")

                // Construct the request body
                body = TextContent(
                    text = """
                        {
                            "model": "text-davinci-003",
                            "prompt": "$prompt",
                            "temperature": 0.1,
                            "max_tokens": 500
                        }
                    """.trimIndent(),
                    contentType = ContentType.Application.Json
                )
            }

            val responseBody = response.bodyAsText()
            Log.d("QuizScreen", "responseBody: $responseBody")
            val jsonResponse = Json.parseToJsonElement(responseBody).jsonObject
            val choicesArray = jsonResponse["choices"]?.jsonArray

            if (!choicesArray.isNullOrEmpty()) {
                val textResponse = choicesArray[0].jsonObject["text"]?.jsonPrimitive?.content ?: ""
                onResponse(textResponse)
            } else {
                onError(Exception("No text found in API response"))
            }
        } catch (e: Exception) {
            onError(e)
            Log.e("QuizScreen", "Error: $e")
        }
    }
}

//Text parsing
data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

fun parseQuestions(text: String): List<Question> {
    val questions = mutableListOf<Question>()
    val lines = text.lines()
    var index = 0
    while (index < lines.size) {
        val questionLine = lines[index++]
        if (questionLine.startsWith("#")) {
            val question = questionLine.substringAfter("#").trim()
            val options = mutableListOf<String>()
            while (index < lines.size && lines[index].startsWith("*")) {
                options.add(lines[index++].substringAfter("*").trim())
            }
            val correctAnswer = lines[index++].substringAfter("Correct:").trim('#')
            questions.add(Question(question, options, correctAnswer))
        }
    }
    return questions
}

@Composable
fun QuizScreen() {
//    val viewModel = viewModel<QuizViewModel>()
//    val quizQuestions by viewModel.quizQuestions.observeAsState(initial = emptyList())

    // State to track whether the API response is received
    var apiResponseReceived by remember { mutableStateOf(false) }

    val subject = "English Language"
    val topic = "Nouns"


    val prompt = "Generate 10 multiple choice questions about $subject and $topic with" +
            " four possible answers each in the form of a multiple choice quiz. " +
            "Add # as delimiters for the questions and * as delimiters for the answers." +
            "Add the correct answer and use the word 'correct' as a delimiter. "
    var response by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

// Effect that runs when the Composable is first composed
    LaunchedEffect(Unit) {
        getOpenAIResponse(
            prompt = prompt,
            onResponse = { apiResponse ->
                response = apiResponse
                apiResponseReceived = true
                Log.d("QuizScreen", "API Response: $apiResponse")
            },
            onError = { error ->
                // Handle error here
                Log.e("QuizScreen", "Error: $error")
            },
            coroutineScope = coroutineScope
        )
    }

     //Display loading animation if the API response is not received
    if (!apiResponseReceived) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RippleLoadingAnimation()
                Text(text = "Generating quiz, please wait...",
                    style = TextStyle(
                        fontFamily = dmSans,
                        fontSize = 18.sp,
                        color = Color(0xFF1B2559),
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
        return
    } else {

        val questions = parseQuestions(response)
        Log.d("QuizScreen", "questions: $questions")


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
                text = "Question ${questions.indexOf(questions[0]) + 1}",
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
                    text = questions[0].question,
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

                items(questions[0].options) { options ->
                    Column(modifier = Modifier.background(color = Color(0xFFF8FAFB))) {
                        val optionsSize = questions[0].options.size - 1
                        when (options) {
                            questions[0].options[0] -> QuizOption(option = options, paddingTop = 20)
//                            , onClick = { viewModel.setUserAnswer(options) })
                            questions[0].options[optionsSize] -> QuizOption(
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

}


@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    Prototype1Theme {
        QuizScreen()
    }
}
