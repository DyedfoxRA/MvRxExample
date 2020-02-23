package com.example.mvrxexample.data.remote

import com.example.mvrxexample.data.repository.CurrencyRepository
import com.example.mvrxexample.domain.mappers.currency.CurrencyResponseToCurrencyMapper
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.utils.exeptions.mapToNoInternetException
import io.reactivex.Single

class RetrofitCurrencyRemoteRepository(
    private val retrofitCurrencyApiService: RetrofitCurrencyApiService,
    private val currencyResponseToCurrencyMapper: CurrencyResponseToCurrencyMapper
) : CurrencyRepository {

    override fun getCategoriesWithStations(): Single<Currency> =
        retrofitCurrencyApiService
            .loadCurrency()
            .onErrorResumeNext { Single.error(it.mapToNoInternetException()) }
            .map(currencyResponseToCurrencyMapper::map)
}