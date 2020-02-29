package com.example.mvrxexample.data.remote

import com.example.mvrxexample.data.response.CurrencyResponse
import com.example.mvrxexample.domain.mappers.currency.CurrencyResponseToCurrencyMapper
import com.example.mvrxexample.domain.model.Currency
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class RetrofitCurrencyRemoteRepositoryTest {

    val retrofitCurrencyApiService: RetrofitCurrencyApiService = mockk(relaxed = true)
    val currencyResponseToCurrencyMapper: CurrencyResponseToCurrencyMapper = mockk(relaxed = true)

    val retrofitCurrencyRemoteRepository = RetrofitCurrencyRemoteRepository(
        retrofitCurrencyApiService,
        currencyResponseToCurrencyMapper
    )

    @Test
    fun `repository returns correct api service response`() {
        every { retrofitCurrencyApiService.loadCurrency() } returns Single.just(
            CurrencyResponse(
                "name",
                emptyMap(),
                "date"
            )
        )
        every { currencyResponseToCurrencyMapper.map(any()) } returns Currency()

        retrofitCurrencyRemoteRepository
            .getCategoriesWithStations()
            .test()
            .assertValue(Currency())
    }

    @Test
    fun `repository returns exception when fail to load response`() {
        val throwable = Throwable()
        every { retrofitCurrencyApiService.loadCurrency() } returns Single.error(
            throwable
        )

        retrofitCurrencyRemoteRepository
            .getCategoriesWithStations()
            .test()
            .assertError(throwable)
    }
}