package com.sunueric.prototype1.ui.composables

import androidx.annotation.DrawableRes

import com.sunueric.prototype1.R

sealed class CustomNavItems (val route: String,
                             @DrawableRes val iconRes: Int,
                             val description: String) {
    object GradesNavItem: CustomNavItems("grades_screen", iconRes = R.drawable.home_icon, "Grades")
    object CoursesNavItem: CustomNavItems("courses_screen", R.drawable.home_icon, "Courses")
    object TopicsNavItem: CustomNavItems("topics_screen", R.drawable.home_icon, "Topics")
    object ReaderNavItem: CustomNavItems("reader_screen", R.drawable.reader_icon, "Reader")
    object QuizNavItem: CustomNavItems("quiz_screen", R.drawable.quiz_icon, "Quiz")
    object QuizResultNavItem: CustomNavItems("quiz_result_screen", R.drawable.quiz_icon, "Quiz Result")
}