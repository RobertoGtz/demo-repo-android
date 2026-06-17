package com.demo.app.data.repositories

import kotlin.test.*
import com.demo.app.domain.usecases.Setting

class SettingsRepositoryTest {

    private lateinit var settingsRepository: SettingsRepository

    @BeforeTest
    fun setUp() {
        settingsRepository = SettingsRepository()
    }

    @Test
    fun testGetSettingsReturnsCorrectValues() {
        settingsRepository.saveSetting("Setting1", "Value1")
        settingsRepository.saveSetting("Setting2", "Value2")
        settingsRepository.saveSetting("Setting3", "Value3")

        val expectedSettings = listOf(
            Setting("Setting1", "Value1"),
            Setting("Setting2", "Value2"),
            Setting("Setting3", "Value3")
        )
        val actualSettings = settingsRepository.getSettings()
        assertEquals(expectedSettings, actualSettings, "The settings returned do not match the expected values.")
    }

    @Test
    fun testSaveSettingAddsNewSetting() {
        val newSetting = Setting("NewSetting", "NewValue")
        settingsRepository.saveSetting(newSetting.key, newSetting.value)
        val settings = settingsRepository.getSettings()
        assertTrue(settings.contains(newSetting), "The new setting was not added correctly.")
    }

    @Test
    fun testRemoveSettingDeletesExistingSetting() {
        val settingToRemove = Setting("SettingToRemove", "ValueToRemove")
        settingsRepository.saveSetting(settingToRemove.key, settingToRemove.value)
        settingsRepository.removeSetting(settingToRemove.key)
        val settings = settingsRepository.getSettings()
        assertFalse(settings.contains(settingToRemove), "The setting was not removed correctly.")
    }
}
