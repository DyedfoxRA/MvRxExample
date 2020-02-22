package com.example.mvrxexample.robot.settings

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.mvrxexample.R
import com.example.mvrxexample.robot.BaseRobot
import com.example.mvrxexample.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_settings.*

fun settingsFragment(func: SettingsRobot.() -> Unit) = SettingsRobot()
    .apply { func() }

class SettingsRobot : BaseRobot() {

    fun hasProperLayout(): ViewInteraction = isDisplayed(R.id.settingsRoot)

    fun clickOnSwitcher(): ViewInteraction =
        Espresso.onView((ViewMatchers.withId(R.id.switcher))).perform(ViewActions.click())

    fun isSwitchDisplayed(): ViewInteraction = isDisplayed(R.id.switcher)

    fun isSwitchNotDisplayed(): ViewInteraction = isGone(R.id.switcher)

    fun Fragment.setDefaultBackground() {
        settingsRoot.setBackgroundResource(R.drawable.gradient_background)
    }

    fun startWithDefaultBackground(): FragmentScenario<SettingsFragment> =
        launchFragmentInContainer<SettingsFragment>()
            .onFragment { fragment: Fragment ->
                fragment.setDefaultBackground()
            }
}