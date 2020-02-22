package com.example.mvrxexample.robot.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.ViewInteraction
import com.example.mvrxexample.R
import com.example.mvrxexample.robot.BaseRobot
import com.example.mvrxexample.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*

fun homeFragment(func: HomeRobot.() -> Unit) = HomeRobot()
    .apply { func() }

class HomeRobot : BaseRobot() {

    fun hasProperLayout(): ViewInteraction = isDisplayed(R.id.homeRoot)

    fun Fragment.setDefaultBackground() {
        homeRoot.setBackgroundResource(R.drawable.gradient_background)
    }

    fun startWithDefaultBackground(): FragmentScenario<HomeFragment> =
        launchFragmentInContainer<HomeFragment>()
            .onFragment { fragment: Fragment ->
                fragment.setDefaultBackground()
            }
}