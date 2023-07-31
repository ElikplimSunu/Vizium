package com.sunueric.prototype1.ui

import Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.theme.Prototype1Theme

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prototype1Theme {
                Navigation(sharedViewModel)
                }
            }
        }
    }

