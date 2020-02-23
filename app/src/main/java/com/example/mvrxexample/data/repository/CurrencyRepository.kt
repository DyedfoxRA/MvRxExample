package com.example.mvrxexample.data.repository

import com.example.mvrxexample.domain.model.Currency
import io.reactivex.Single

interface CurrencyRepository {

    fun getCategoriesWithStations(): Single<Currency>
}