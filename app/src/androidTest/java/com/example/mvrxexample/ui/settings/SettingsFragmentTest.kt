package com.example.mvrxexample.ui.settings

import com.example.mvrxexample.R
import com.example.mvrxexample.domain.interactors.settings.GetSwitchSettings
import com.example.mvrxexample.robot.settings.settingsFragment
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.mock.declare

class SettingsFragmentTest : KoinTest {

    val getSwitchSettings: GetSwitchSettings = mockk()

    @Before
    fun setUp() {
        declare {
            single { getSwitchSettings }
        }
        every { getSwitchSettings() } returns Single.just(false)
    }

    @Test
    fun layout_id_is_equals_fragment_radio() {
        settingsFragment {
            startWithDefaultBackground()
            hasProperLayout()
        }
    }

    @Test
    fun view_shows_notification_settings_text_views_and_switcher() {
        settingsFragment {
            startWithDefaultBackground()
            isDisplayed("Switch")
            isDisplayed("if you enable it nothing will change just put state to sharePrefs")
            isDisplayed(R.id.switcher)
        }
    }

    @Test
    fun view_shows_notification_switcher() {
        settingsFragment {
            startWithDefaultBackground()
            isSwitchDisplayed()
        }
    }

    @Test
    fun perform_click() {
        settingsFragment {
            startWithDefaultBackground()
            clickOnSwitcher()
        }
    }
}