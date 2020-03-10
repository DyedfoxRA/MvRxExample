package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.data.repository.CurrencyRepository
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.reactivex.Single

class GetCurrencyFromRemote(
    private val remoteRepository: CurrencyRepository
) {

    operator fun invoke(): Single<Currency> =
        remoteRepository.getCategoriesWithStations().map { cur ->
            Currency(
                cur.name,
                cur.rates.map { rate -> Rate(rate.name, rate.value, rate.number * rate.value) },
                cur.date
            )
        }
}