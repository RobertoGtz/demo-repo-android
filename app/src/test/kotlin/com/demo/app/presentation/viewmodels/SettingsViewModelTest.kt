package com.demo.app.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import com.demo.app.domain.usecases.GetSettingsUseCase
import com.demo.app.domain.usecases.UpdateSettingsUseCase
import com.demo.app.presentation.viewmodels.SettingsViewModel.UiState

class SettingsViewModelTest {

    private lateinit var getSettingsUseCase: GetSettingsUseCase
    private lateinit var updateSettingsUseCase: UpdateSettingsUseCase
    private lateinit var settingsViewModel: SettingsViewModel

    @BeforeTest
    fun setUp() {
        getSettingsUseCase = GetSettingsUseCase()
        updateSettingsUseCase = UpdateSettingsUseCase()
        settingsViewModel = SettingsViewModel(getSettingsUseCase, updateSettingsUseCase)
    }

    @Test
    fun `test initial state is loading`() {
        val initialState = settingsViewModel.uiState
        assertTrue(initialState is UiState.Loading)
    }

    @Test
    fun `test load settings success`() {
        val expectedSettings = listOf("Setting1", "Setting2")
        getSettingsUseCase.stub { expectedSettings }

        settingsViewModel.loadSettings()

        val successState = settingsViewModel.uiState as UiState.Success
        assertEquals(expectedSettings, successState.items)
    }

    @Test
    fun `test load settings empty`() {
        getSettingsUseCase.stub { emptyList<String>() }

        settingsViewModel.loadSettings()

        assertTrue(settingsViewModel.uiState is UiState.Empty)
    }

    @Test
    fun `test update settings success`() {
        val newSettings = listOf("NewSetting1", "NewSetting2")
        updateSettingsUseCase.stub { newSettings }

        settingsViewModel.updateSettings(newSettings)

        val successState = settingsViewModel.uiState as UiState.Success
        assertEquals(newSettings, successState.items)
    }
}