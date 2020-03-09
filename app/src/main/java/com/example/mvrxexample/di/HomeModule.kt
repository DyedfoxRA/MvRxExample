package com.example.mvrxexample.di

import com.example.mvrxexample.ui.home.HomeState
import com.example.mvrxexample.ui.home.HomeViewModel
import org.koin.dsl.module

internal val homeModule = module {

    factory { (state: HomeState) ->
        HomeViewModel(
            state,
            getCurrencyFromRemote = get(),
            calculateCurrencies = get(),
            rxTransformer = get(),
            ioTransformer = get()
        )
    }
}