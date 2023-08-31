package com.sunueric.prototype1.ui.utils

sealed class Screens(val route: String) {
    object SignIn : Screens("sign_in_screen")
    object SignUp : Screens("sign_up_screen")
    object Grades : Screens("grades_screen")
    object Courses : Screens("courses_screen")
    object Topics : Screens("topics_screen")
    object Reader : Screens("reader_screen")
    object Quiz : Screens("quiz_screen")
    object FirstOnBoadingScreen : Screens("first_onboarding_screen")
    object SecondOnBoadingScreen : Screens("second_onboarding_screen")
    object ThirdOnBoadingScreen : Screens("third_onboarding_screen")

    object QuizResult : Screens("quiz_result_screen")
}