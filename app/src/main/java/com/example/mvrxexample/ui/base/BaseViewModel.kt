package com.example.mvrxexample.ui.base

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.example.mvrxexample.BuildConfig

abstract class BaseViewModel<S : MvRxState>(initialState: S) :
    BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG) {

    fun <T> Async<T>.successAndComplete(): Boolean = (this is Success) and complete
}