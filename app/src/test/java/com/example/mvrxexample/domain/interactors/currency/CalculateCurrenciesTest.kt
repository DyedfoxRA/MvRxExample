package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class CalculateCurrenciesTest {

    val getCurrencyFromRemote: GetCurrencyFromRemote = mockk(relaxed = true)
    val calculateCurrencies = CalculateCurrencies(getCurrencyFromRemote)

    @Test
    fun `invokes return correct value`() {

        val currency =
            Currency(
                "EUR",
                listOf(Rate("EUR", 1.0, 1.0), Rate("USD", 2.0, 2.0)),
                "123"
            )
        val newCurrency =
            Currency(
                "EUR",
                listOf(Rate("EUR", 1.0, 5.0), Rate("USD", 2.0, 10.0)),
                "123"
            )

        every { getCurrencyFromRemote() } returns Single.just(currency)

        calculateCurrencies(5.0, Rate("EUR", 1.0, 1.0)).test()
            .assertValue(newCurrency)
    }
}