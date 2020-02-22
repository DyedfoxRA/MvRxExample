package com.example.mvrxexample.ui.home

import com.example.mvrxexample.robot.home.homeFragment
import org.junit.Test
import org.koin.test.KoinTest

class HomeFragmentTest : KoinTest {

    @Test
    fun layout_id_is_equals_fragment_radio() {
        homeFragment {
            startWithDefaultBackground()
            hasProperLayout()
        }
    }
}