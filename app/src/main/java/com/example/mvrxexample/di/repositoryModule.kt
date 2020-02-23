package com.example.mvrxexample.di

import com.example.mvrxexample.data.remote.RetrofitCurrencyRemoteRepository
import com.example.mvrxexample.data.repository.CurrencyRepository
import com.example.mvrxexample.data.settings.SettingsRepository
import com.example.mvrxexample.framework.data.settings.SettingsSharedPrefsRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<SettingsRepository> { SettingsSharedPrefsRepository(sharedPreferences = get()) }

    single<CurrencyRepository> {
        RetrofitCurrencyRemoteRepository(
            retrofitCurrencyApiService = get(),
            currencyResponseToCurrencyMapper = get()
        )
    }
}