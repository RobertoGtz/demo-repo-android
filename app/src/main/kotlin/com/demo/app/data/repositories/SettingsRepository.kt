package com.demo.app.data.repositories

import com.demo.app.domain.usecases.Setting

class SettingsRepository {

    private val settingsData: MutableMap<String, String> = mutableMapOf()

    fun saveSetting(key: String, value: String) {
        settingsData[key] = value
    }

    fun getSettings(): List<Setting> {
        return settingsData.map { Setting(it.key, it.value) }
    }

    fun removeSetting(key: String) {
        settingsData.remove(key)
    }

    fun clearAllSettings() {
        settingsData.clear()
    }
}
