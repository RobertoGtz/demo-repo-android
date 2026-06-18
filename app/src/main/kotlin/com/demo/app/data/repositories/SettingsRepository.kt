package com.demo.app.data.repositories

class SettingsRepository {

    private val settingsData: MutableMap<String, Any> = mutableMapOf()

    fun saveSetting(key: String, value: Any) {
        settingsData[key] = value
    }

    fun getSetting(key: String): Any? {
        return settingsData[key]
    }

    fun removeSetting(key: String) {
        settingsData.remove(key)
    }

    fun clearAllSettings() {
        settingsData.clear()
    }
}