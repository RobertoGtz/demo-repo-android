package com.demo.app.domain.usecases

import com.demo.app.data.repositories.SettingsRepository

class UpdateSettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun execute(setting: Setting): List<Setting> {
        settingsRepository.saveSetting(setting.key, setting.value)
        return settingsRepository.getSettings()
    }
}
