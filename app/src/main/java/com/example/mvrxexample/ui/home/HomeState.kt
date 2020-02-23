package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.MvRxState
import com.example.mvrxexample.domain.model.Currency

data class HomeState(
    val currency: Currency?
) : MvRxState