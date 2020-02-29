package com.example.mvrxexample.domain.interactors.currency

import com.example.mvrxexample.data.repository.CurrencyRepository
import com.example.mvrxexample.domain.model.Currency
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class GetCurrencyFromRemoteTest {

    val remoteRepository: CurrencyRepository = mockk(relaxed = true)
    val getCurrencyFromRemote = GetCurrencyFromRemote(remoteRepository)

    @Test
    fun `invoke returns correct value from remote repository`() {
        every { remoteRepository.getCategoriesWithStations() } returns Single.just(Currency())

        getCurrencyFromRemote().test().assertValue(Currency())
    }
}