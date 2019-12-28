package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class HomeState(
    val someState: Async<Boolean> = Uninitialized
) : MvRxState