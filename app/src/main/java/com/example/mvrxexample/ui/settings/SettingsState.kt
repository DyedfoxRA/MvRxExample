package com.example.mvrxexample.ui.settings

import com.airbnb.mvrx.MvRxState

data class SettingsState(
    val isSwitcherEnabled: Boolean? = false
) : MvRxState