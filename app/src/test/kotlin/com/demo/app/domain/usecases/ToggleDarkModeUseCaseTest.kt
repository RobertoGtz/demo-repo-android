package com.demo.app.domain.usecases

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ToggleDarkModeUseCaseTest {

    private lateinit var toggleDarkModeUseCase: ToggleDarkModeUseCase
    private lateinit var settingsRepository: FakeSettingsRepository

    @BeforeTest
    fun setUp() {
        settingsRepository = FakeSettingsRepository()
        toggleDarkModeUseCase = ToggleDarkModeUseCase(settingsRepository)
    }

    @Test
    fun `test toggle dark mode from off to on`() {
        settingsRepository.setDarkModeEnabled(false)
        toggleDarkModeUseCase.execute()
        assertEquals(true, settingsRepository.isDarkModeEnabled())
    }

    @Test
    fun `test toggle dark mode from on to off`() {
        settingsRepository.setDarkModeEnabled(true)
        toggleDarkModeUseCase.execute()
        assertEquals(false, settingsRepository.isDarkModeEnabled())
    }

    private class FakeSettingsRepository : SettingsRepository {
        private var darkModeEnabled = false

        override fun isDarkModeEnabled(): Boolean {
            return darkModeEnabled
        }

        override fun setDarkModeEnabled(enabled: Boolean) {
            darkModeEnabled = enabled
        }
    }
}