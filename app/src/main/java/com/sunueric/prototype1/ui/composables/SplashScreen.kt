package com.sunueric.prototype1.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.utils.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    rememberSystemUiController().apply {
        this.isNavigationBarVisible = false
        this.isStatusBarVisible = false
        this.isSystemBarsVisible = false
    }

    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.navigate(Screens.Grades.route) {
            popUpTo(0)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFE5E5E5)), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.baseline_menu_book_24),
            contentDescription = "Splash Screen")
    }
}