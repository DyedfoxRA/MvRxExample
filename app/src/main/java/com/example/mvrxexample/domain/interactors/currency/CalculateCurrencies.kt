package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.reactivex.Observable

class CalculateCurrencies(
    private val getCurrencyFromRemote: GetCurrencyFromRemote
) {

    operator fun invoke(value: Double, rate: Rate): Observable<Currency> =
        getCurrencyFromRemote().flatMapObservable {
            Observable.just(Currency(it.name, cal(value, it.rates, rate), it.date))
        }

    private fun cal(value: Double, rates: List<Rate>, rate1: Rate): MutableList<Rate> {
        val newList: MutableList<Rate> = mutableListOf()
        for (rate in rates) {
            newList.add(
                Rate(
                    rate.name,
                    rate.value,
                    cutNumberTwoDigitsAfterComma(rate.value * value / rate1.value)
                )
            )
        }
        return newList
    }

    private fun cutNumberTwoDigitsAfterComma(number: Double) =
        "%.2f".format(number).toDouble()
}