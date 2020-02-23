package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.data.repository.CurrencyRepository
import com.example.mvrxexample.domain.model.Currency
import io.reactivex.Single

class GetCurrencyFromRemote(
    private val remoteRepository: CurrencyRepository
) {

    operator fun invoke(): Single<Currency> =
        remoteRepository.getCategoriesWithStations()
}