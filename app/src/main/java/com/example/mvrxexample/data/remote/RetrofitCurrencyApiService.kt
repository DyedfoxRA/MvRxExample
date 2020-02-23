package com.example.mvrxexample.data.remote

import com.example.mvrxexample.data.response.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitCurrencyApiService {

    @GET("latest?base=EUR")
    fun loadCurrency(): Single<CurrencyResponse>
}