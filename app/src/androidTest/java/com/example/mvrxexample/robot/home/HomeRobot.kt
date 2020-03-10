package com.example.mvrxexample.robot.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.ViewInteraction
import com.example.mvrxexample.R
import com.example.mvrxexample.robot.BaseRobot
import com.example.mvrxexample.ui.home.HomeFragment
import com.schibsted.spain.barista.assertion.BaristaHintAssertions
import com.schibsted.spain.barista.interaction.BaristaClickInteractions
import kotlinx.android.synthetic.main.fragment_home.*

fun homeFragment(func: HomeRobot.() -> Unit) = HomeRobot()
    .apply { func() }

class HomeRobot : BaseRobot() {

    fun hasProperLayout(): ViewInteraction = isDisplayed(R.id.homeRoot)

    fun isEditTextDisplayed(): ViewInteraction = isDisplayed(R.id.enter_value)

    fun isSelectedCurrencyDisplayed(): ViewInteraction = isDisplayed(R.id.currency_main)

    fun isCancelDisplayed(): ViewInteraction = isDisplayed(R.id.cancel)

    fun matchTextHintForSearchEditText(text: String) =
        BaristaHintAssertions.assertHint(R.id.enter_value, text)

    fun clickOnEditInput() = BaristaClickInteractions.clickOn(R.id.enter_value)

    fun Fragment.setDefaultBackground() {
        homeRoot.setBackgroundResource(R.drawable.gradient_background)
    }

    fun startWithDefaultBackground(): FragmentScenario<HomeFragment> =
        launchFragmentInContainer<HomeFragment>()
            .onFragment { fragment: Fragment ->
                fragment.setDefaultBackground()
            }
}