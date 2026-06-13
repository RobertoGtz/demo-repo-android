package com.demo.app.data

import android.content.Context
import android.content.SharedPreferences

class NotificationPreferencesRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setNotificationEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_NOTIFICATION_ENABLED, isEnabled).apply()
    }

    fun isNotificationEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_NOTIFICATION_ENABLED, DEFAULT_NOTIFICATION_ENABLED)
    }

    fun setNotificationSoundEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_NOTIFICATION_SOUND_ENABLED, isEnabled).apply()
    }

    fun isNotificationSoundEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_NOTIFICATION_SOUND_ENABLED, DEFAULT_NOTIFICATION_SOUND_ENABLED)
    }

    fun setNotificationVibrationEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_NOTIFICATION_VIBRATION_ENABLED, isEnabled).apply()
    }

    fun isNotificationVibrationEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_NOTIFICATION_VIBRATION_ENABLED, DEFAULT_NOTIFICATION_VIBRATION_ENABLED)
    }

    companion object {
        private const val PREFS_NAME = "notification_preferences"
        private const val KEY_NOTIFICATION_ENABLED = "notification_enabled"
        private const val KEY_NOTIFICATION_SOUND_ENABLED = "notification_sound_enabled"
        private const val KEY_NOTIFICATION_VIBRATION_ENABLED = "notification_vibration_enabled"
        private const val DEFAULT_NOTIFICATION_ENABLED = true
        private const val DEFAULT_NOTIFICATION_SOUND_ENABLED = true
        private const val DEFAULT_NOTIFICATION_VIBRATION_ENABLED = true
    }
}