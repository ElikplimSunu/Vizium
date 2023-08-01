package com.sunueric.prototype1.ui.utils

sealed class Screens(val route: String) {
    object SpashScreen : Screens("splash_screen")
    object Grades : Screens("grades_screen")
    object Courses : Screens("courses_screen")
    object Topics : Screens("topics_screen")
    object Reader : Screens("reader_screen")
    object Quiz : Screens("quiz_screen")

    object QuizResult : Screens("quiz_result_screen")
}