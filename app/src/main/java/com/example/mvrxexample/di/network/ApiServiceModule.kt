package com.example.mvrxexample.di.network

import com.example.mvrxexample.data.remote.RetrofitCurrencyApiService
import org.koin.dsl.module
import retrofit2.Retrofit

internal val apiServiceModule = module {

    single { get<Retrofit>().create(RetrofitCurrencyApiService::class.java) }
}