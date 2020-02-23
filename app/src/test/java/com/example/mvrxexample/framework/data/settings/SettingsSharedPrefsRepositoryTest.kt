package com.example.mvrxexample.framework.data.settings

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SettingsSharedPrefsRepositoryTest {

    private val editor: SharedPreferences.Editor = mockk()
    private val sharedPrefs: SharedPreferences = mockk()
    private val settingsSharedPrefsRepository = SettingsSharedPrefsRepository(sharedPrefs)

    @Before
    fun setUp() {
        every { sharedPrefs.edit() } returns editor
        every { editor.putBoolean(any(), any()) } returns editor
        every { editor.commit() } returns true
    }

    @Test
    fun saveNotificationsSettings_saves_value_in_sharedPrefs() {
        settingsSharedPrefsRepository.saveSwitchState(true).test()
        verify { sharedPrefs.edit() }
        verify { editor.putBoolean("SETTINGS_SWITCH_ENABLED", true) }
        verify { editor.commit() }
    }

    @Test
    fun getNotificationsSettings_loads_value_from_sharedPrefs_with_proper_def_value() {
        settingsSharedPrefsRepository.getSwitchState().test()
        verify { sharedPrefs.getBoolean("SETTINGS_SWITCH_ENABLED", true) }
    }
}