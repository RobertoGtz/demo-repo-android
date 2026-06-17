package com.demo.app.domain.usecases

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import com.demo.app.data.repositories.SettingsRepository

class GetSettingsUseCaseTest {

    private lateinit var getSettingsUseCase: GetSettingsUseCase
    private lateinit var settingsRepository: SettingsRepository

    @BeforeTest
    fun setUp() {
        settingsRepository = SettingsRepository()
        getSettingsUseCase = GetSettingsUseCase(settingsRepository)
    }

    @Test
    fun `test getSettings returns non-empty list`() {
        // Arrange
        settingsRepository.saveSetting("DarkMode", "Enabled")
        settingsRepository.saveSetting("Notifications", "On")

        // Act
        val settings = getSettingsUseCase.execute()

        // Assert
        assertTrue(settings.isNotEmpty(), "Expected non-empty settings list")
        assertEquals(2, settings.size, "Expected settings list size to be 2")
        assertEquals("DarkMode", settings[0].key, "Expected first setting key to be 'DarkMode'")
        assertEquals("Enabled", settings[0].value, "Expected first setting value to be 'Enabled'")
    }

    @Test
    fun `test getSettings returns empty list when no settings available`() {
        // Act
        val settings = getSettingsUseCase.execute()

        // Assert
        assertTrue(settings.isEmpty(), "Expected empty settings list")
    }
}
