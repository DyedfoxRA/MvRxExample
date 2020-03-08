package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.reactivex.Observable

class CalculateCurrencies {

    operator fun invoke(value: Double, currency: Currency): Observable<Currency> =
        Observable.just(Currency(currency.name, cal(value, currency.rates), currency.date))

    private fun cal(value: Double, rates: List<Rate>): MutableList<Rate> {
        val newList: MutableList<Rate> = mutableListOf()
        for (a in rates) {
            newList.add(Rate(a.name, a.value, a.number * value))
        }
        return newList
    }
}