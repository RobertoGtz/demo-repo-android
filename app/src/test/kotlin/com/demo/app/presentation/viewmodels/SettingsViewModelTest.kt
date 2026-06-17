package com.demo.app.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.demo.app.domain.usecases.GetSettingsUseCase
import com.demo.app.domain.usecases.UpdateSettingsUseCase
import com.demo.app.domain.usecases.Setting
import com.demo.app.data.repositories.SettingsRepository
import com.demo.app.presentation.viewmodels.SettingsViewModel.UiState

class SettingsViewModelTest {

    private val settingsRepository = SettingsRepository()
    private val getSettingsUseCase = GetSettingsUseCase(settingsRepository)
    private val updateSettingsUseCase = UpdateSettingsUseCase(settingsRepository)
    private val viewModel = SettingsViewModel(getSettingsUseCase, updateSettingsUseCase)

    @Test
    fun `test get settings success`() {
        settingsRepository.saveSetting("DarkMode", "Enabled")
        settingsRepository.saveSetting("Notifications", "On")

        val state = viewModel.getSettings()
        assertTrue(state is UiState.Success)
        val successState = state as UiState.Success
        assertEquals(2, successState.items.size)
    }

    @Test
    fun `test get settings empty`() {
        val state = viewModel.getSettings()
        assertTrue(state is UiState.Empty)
    }

    @Test
    fun `test update settings`() {
        val setting = Setting("DarkMode", "Disabled")
        val state = viewModel.updateSettings(setting)
        assertTrue(state is UiState.Success)
        val successState = state as UiState.Success
        assertEquals(1, successState.items.size)
        assertEquals("Disabled", successState.items[0].value)
    }
}
