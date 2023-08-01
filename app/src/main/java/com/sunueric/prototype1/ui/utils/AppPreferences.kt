package com.sunueric.prototype1.ui.utils

import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.core.content.ContextCompat.getSystemService

object AppPreferences {
    private const val PREFERENCE_FILE_KEY = "app_preferences"
    private const val TALKBACK_ENABLED_KEY = "talkback_enabled"

    fun isTalkBackEnabled(context: Context): Boolean {
        val accessibilityManager =
            getSystemService(context, AccessibilityManager::class.java)
        if (accessibilityManager != null) {
            return accessibilityManager.isTouchExplorationEnabled
        }
        return false
    }

    fun setTalkBackEnabled(context: Context, isEnabled: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(TALKBACK_ENABLED_KEY, isEnabled)
            apply()
        }
    }

    fun getTalkBackEnabled(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(TALKBACK_ENABLED_KEY, false)
    }
}