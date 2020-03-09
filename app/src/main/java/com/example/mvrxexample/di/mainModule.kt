package com.example.mvrxexample.di

import android.content.Context
import android.preference.PreferenceManager
import android.view.inputmethod.InputMethodManager
import com.example.mvrxexample.utils.rx.AppTransformer
import com.example.mvrxexample.utils.rx.Transformer
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val mainModule = module {
    single<Transformer> { AppTransformer() }
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    single {
        androidApplication().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}