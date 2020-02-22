package com.example.mvrxexample.robot.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.ViewInteraction
import com.example.mvrxexample.R
import com.example.mvrxexample.robot.BaseRobot
import com.example.mvrxexample.ui.info.InfoFragment
import kotlinx.android.synthetic.main.fragment_info.*

fun infoFragment(func: InfoRobot.() -> Unit) = InfoRobot()
    .apply { func() }

class InfoRobot : BaseRobot() {

    fun hasProperLayout(): ViewInteraction = isDisplayed(R.id.infoRoot)

    fun Fragment.setDefaultBackground() {
        infoRoot.setBackgroundResource(R.drawable.gradient_background)
    }

    fun startWithDefaultBackground(): FragmentScenario<InfoFragment> =
        launchFragmentInContainer<InfoFragment>()
            .onFragment { fragment: Fragment ->
                fragment.setDefaultBackground()
            }
}