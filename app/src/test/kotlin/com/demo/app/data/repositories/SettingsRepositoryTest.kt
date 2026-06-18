package com.demo.app.data.repositories

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SettingsRepositoryTest {

    private lateinit var settingsRepository: SettingsRepository

    @BeforeTest
    fun setUp() {
        settingsRepository = SettingsRepository()
    }

    @Test
    fun `test getSettings returns default settings`() {
        val settings = settingsRepository.getSettings()
        assertTrue(settings.isNotEmpty(), "Settings should not be empty")
        // Assuming default settings contain a specific key-value pair
        assertEquals("default_value", settings["default_key"], "Default setting value mismatch")
    }

    @Test
    fun `test updateSetting updates the setting correctly`() {
        val key = "test_key"
        val value = "test_value"
        settingsRepository.updateSetting(key, value)
        
        val settings = settingsRepository.getSettings()
        assertEquals(value, settings[key], "Setting value should be updated")
    }

    @Test
    fun `test clearSettings removes all settings`() {
        settingsRepository.updateSetting("key1", "value1")
        settingsRepository.updateSetting("key2", "value2")
        
        settingsRepository.clearSettings()
        
        val settings = settingsRepository.getSettings()
        assertTrue(settings.isEmpty(), "Settings should be empty after clear")
    }
}