package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.utils.rx.TestTransformer
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mvRxRule = MvRxTestRule()

    val initState = HomeState()

    val getCurrencyFromRemote: GetCurrencyFromRemote = mockk(relaxed = true)

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            initState,
            getCurrencyFromRemote,
            TestTransformer()
        )
    }

    @Test
    fun `init set correct state for currency`() {
        every { getCurrencyFromRemote.invoke() } returns Single.just(Currency())

        viewModel.preLoadCurrency()

        withState(viewModel) { state ->
            assertEquals(Currency(), state.currency)
        }
    }
}