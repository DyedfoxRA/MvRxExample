package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.reactivex.Observable

class CalculateCurrencies(
    private val getCurrencyFromRemote: GetCurrencyFromRemote
) {

    operator fun invoke(value: Double, selectedRate: Rate): Observable<Currency> =
        getCurrencyFromRemote().flatMapObservable { currency ->
            Observable.just(
                Currency(
                    currency.name,
                    currency.rates.calculate(value, selectedRate),
                    currency.date
                )
            )
        }

    private fun List<Rate>.calculate(value: Double, selectedRate: Rate): List<Rate> =
        map { rate ->
            Rate(
                rate.name,
                rate.value,
                cutNumberTwoDigitsAfterComma(rate.value * value / selectedRate.value)
            )
        }

    private fun cutNumberTwoDigitsAfterComma(number: Double) =
        "%.2f".format(number).toDouble()
}