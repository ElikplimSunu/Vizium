package com.sunueric.prototype1.ui

import Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sunueric.prototype1.ui.theme.Prototype1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prototype1Theme {
                Navigation()
                }
            }
        }
    }

