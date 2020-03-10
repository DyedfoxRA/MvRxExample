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

    @Test
    fun invalidate_currency_main_text() {
        homeFragment {
            startWithDefaultBackground()
            isSelectedCurrencyDisplayed()
        }
    }

    @Test
    fun invalidate_shows_edit_text() {
        homeFragment {
            startWithDefaultBackground()
            isEditTextDisplayed()
        }
    }

    @Test
    fun invalidate_shows_cancel_text() {
        homeFragment {
            startWithDefaultBackground()
            isEditTextDisplayed()
        }
    }
}