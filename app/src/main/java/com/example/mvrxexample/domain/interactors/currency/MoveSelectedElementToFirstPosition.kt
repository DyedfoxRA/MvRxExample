package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.reactivex.Single

class MoveSelectedElementToFirstPosition {

    operator fun invoke(rate: Rate, currency: Currency): Single<Currency> =
        Single.just(Currency(currency.name, replace(currency.rates, rate), currency.date))

    private fun replace(rates: List<Rate>, rate: Rate): MutableList<Rate> {
        val newList = rates.toMutableList()
        newList.remove(rate)
        newList.add(0, rate)
        return newList
    }
}