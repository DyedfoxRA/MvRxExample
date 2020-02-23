package com.example.mvrxexample.di

import android.preference.PreferenceManager
import com.example.mvrxexample.utils.rx.AppTransformer
import com.example.mvrxexample.utils.rx.Transformer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val mainModule = module {
    single<Transformer> { AppTransformer() }
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}