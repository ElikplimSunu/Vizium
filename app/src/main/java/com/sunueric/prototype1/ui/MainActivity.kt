package com.sunueric.prototype1.ui

import Navigation
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.utils.AppPreferences

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val talkBackEnabled = AppPreferences.isTalkBackEnabled(applicationContext)
        sharedViewModel.setTalkBackEnabled(talkBackEnabled)

        if (talkBackEnabled) {
            Log.d("MainActivity", "TalkBack is enabled")
        } else {
            Log.d("MainActivity", "TalkBack is disabled")
        }

        setContent {
            Prototype1Theme {
                Navigation(sharedViewModel)
            }
        }
    }
}

