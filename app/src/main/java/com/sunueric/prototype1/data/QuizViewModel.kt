package com.sunueric.prototype1.data//package com.sunueric.prototype1
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.Header
//import retrofit2.http.POST
//
//class QuizViewModel: ViewModel() {
//    private val openAIApiKey = ""
//
//    private val _quizQuestions = MutableLiveData<List<String>>()
//    val quizQuestions: LiveData<List<String>> = _quizQuestions
//
//    private val _userAnswers = MutableLiveData<List<String>>()
//    val userAnswers: LiveData<List<String>> = _userAnswers
//
//    var currentQuestionIndex = 0
//        private set
//
//
//
//    fun setUserAnswer(answer: String) {
//        val updatedUserAnswers = _userAnswers.value.orEmpty().toMutableList()
//        updatedUserAnswers[currentQuestionIndex] = answer
//        _userAnswers.value = updatedUserAnswers
//        if (currentQuestionIndex < _quizQuestions.value.orEmpty().size - 1) {
//            currentQuestionIndex++
//        }
//    }
//
//    fun generateQuizQuestions(subject: String, topic: String) {
//        val openAIInterface = Retrofit.Builder()
//            .baseUrl("https://api.openai.com/v1/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(OpenAIInterface::class.java)
//
//        val request = QuizRequest(
//            prompt = "Generate 10 quiz questions about $subject and $topic with four possible answers each." +
//                    "in the form of a multiple choice quiz.",
//            temperature = 0.5,
//            maxTokens = 32
//        )
//
//        openAIInterface.generateQuizQuestions(openAIApiKey, request).enqueue(object :
//            Callback<QuizResponse> {
//            override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
//                if (response.isSuccessful) {
//                    val quizQuestions = response.body()?.choices?.get(0)?.text
//                        ?.split("\n")
//                        ?.filter { it.isNotBlank() }
//                        ?: emptyList()
//
//                    _quizQuestions.postValue(quizQuestions)
//                } else {
//                    // handle error
//                }
//            }
//
//            override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
//                // handle error
//            }
//        })
//    }
//}
//
//data class QuizRequest(
//    val prompt: String,
//    val temperature: Double,
//    val maxTokens: Int
//)
//
//data class QuizResponse(
//    val choices: List<QuizChoice>
//)
//
//data class QuizChoice(
//    val text: String,
//    val index: Int,
//    val logprobs: Any?,
//    val finishReason: String?
//)
//
//interface OpenAIInterface {
//    @POST("completions")
//    fun generateQuizQuestions(
//        @Header("Authorization") apiKey: String,
//        @Body request: QuizRequest
//    ): Call<QuizResponse>
//}