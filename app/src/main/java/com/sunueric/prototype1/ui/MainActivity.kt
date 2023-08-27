package com.sunueric.prototype1.ui

import Navigation
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.auth.AuthViewModel
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                sharedViewModel.isLoading.value
            }
        }

        val talkBackEnabled = AppPreferences.isTalkBackEnabled(applicationContext)
        sharedViewModel.setTalkBackEnabled(talkBackEnabled)

        if (talkBackEnabled) {
            Log.d("MainActivity", "TalkBack is enabled")
        } else {
            Log.d("MainActivity", "TalkBack is disabled")
        }

        setContent {
            Prototype1Theme {
                Navigation(sharedViewModel, authViewModel)
            }
        }
    }
}

