package com.example.mvrxexample.di.utils

import com.google.gson.Gson
import org.koin.dsl.module

internal val gsonModule = module {

    factory { Gson() }
}