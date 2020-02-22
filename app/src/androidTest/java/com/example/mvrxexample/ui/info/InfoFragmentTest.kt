package com.example.mvrxexample.ui.info

import com.example.mvrxexample.robot.info.infoFragment
import org.junit.Test

class InfoFragmentTest {

    @Test
    fun layout_id_is_equals_fragment_radio() {
        infoFragment {
            startWithDefaultBackground()
            hasProperLayout()
        }
    }
}