package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate

data class HomeState(
    val currency: Async<Currency?> = Uninitialized,
    val selectedCurrency: Async<Rate> = Uninitialized,
    val writtenNumber: String = "1"
) : MvRxState