package com.demo.app.domain.usecases

import com.demo.app.data.repositories.SettingsRepository

data class Setting(val key: String, val value: String)

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun execute(): List<Setting> {
        return settingsRepository.getSettings()
    }
}
