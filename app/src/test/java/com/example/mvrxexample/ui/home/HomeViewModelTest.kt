package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.example.mvrxexample.domain.interactors.currency.CalculateCurrencies
import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import com.example.mvrxexample.utils.rx.TestTransformer
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
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
    val calculateCurrencies: CalculateCurrencies = mockk(relaxed = true)

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            initState,
            getCurrencyFromRemote,
            calculateCurrencies,
            TestTransformer()
        )
    }

    @Test
    fun `init set correct state for currency`() {
        every { getCurrencyFromRemote() } returns Single.just(Currency())

        viewModel.preLoadCurrency()

        withState(viewModel) { state ->
            assertEquals(Currency(), state.currency())
        }
    }

    @Test
    fun `calculate set correct state for currency and written number`() {
        every { calculateCurrencies(1.0, Rate()) } returns Observable.just(Currency())
        val number = 1.0

        viewModel.calculateCurrency(number.toString())

        withState(viewModel) { state ->
            assertEquals(Currency(), state.currency())
            assertEquals(number.toString(), state.writtenNumber)
        }
    }

    @Test
    fun `cancel set correct state for written number`() {
        viewModel.cancelSearch()

        withState(viewModel) { state ->
            assertEquals("1", state.writtenNumber)
        }
    }

    @Test
    fun `select rate set correct state for written number`() {
        viewModel.selectCurrency(Rate())

        withState(viewModel) { state ->
            assertEquals(Rate(), state.selectedCurrency())
        }
    }
}