package com.demo.app.presentation.ui

import com.demo.app.presentation.viewmodels.SettingsViewModel
import com.demo.app.presentation.viewmodels.SettingsViewModel.UiState
import com.demo.app.domain.usecases.GetSettingsUseCase
import com.demo.app.domain.usecases.UpdateSettingsUseCase
import com.demo.app.domain.usecases.Setting
import com.demo.app.data.repositories.SettingsRepository

class SettingsFragment {

    private val settingsRepository = SettingsRepository()
    private val getSettingsUseCase = GetSettingsUseCase(settingsRepository)
    private val updateSettingsUseCase = UpdateSettingsUseCase(settingsRepository)
    private val viewModel = SettingsViewModel(getSettingsUseCase, updateSettingsUseCase)

    fun displaySettings() {
        when (val state = viewModel.getSettings()) {
            is UiState.Loading -> showLoading()
            is UiState.Success -> showSettings(state.items)
            is UiState.Empty -> showEmptyState()
        }
    }

    private fun showLoading() {
        println("Loading settings...")
    }

    private fun showSettings(settings: List<Setting>) {
        println("Settings: ${settings.joinToString { "${it.key}: ${it.value}" }}")
    }

    private fun showEmptyState() {
        println("No settings available.")
    }
}
