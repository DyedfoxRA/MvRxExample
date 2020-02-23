package com.example.mvrxexample.di

import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import org.koin.dsl.module

internal val currencyModule = module {
    factory { GetCurrencyFromRemote(remoteRepository = get()) }
}