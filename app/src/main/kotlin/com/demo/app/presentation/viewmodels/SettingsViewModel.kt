package com.demo.app.presentation.viewmodels

import com.demo.app.domain.usecases.GetSettingsUseCase
import com.demo.app.domain.usecases.UpdateSettingsUseCase

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase
) {

    sealed class UiState<out T> {
        object Loading : UiState<Nothing>()
        data class Success<T>(val items: List<T>) : UiState<T>()
        object Empty : UiState<Nothing>()
    }

    fun getSettings(): UiState<Setting> {
        val settings = getSettingsUseCase.execute()
        return if (settings.isNotEmpty()) {
            UiState.Success(settings)
        } else {
            UiState.Empty
        }
    }

    fun updateSettings(setting: Setting): UiState<Setting> {
        updateSettingsUseCase.execute(setting)
        return getSettings()
    }
}