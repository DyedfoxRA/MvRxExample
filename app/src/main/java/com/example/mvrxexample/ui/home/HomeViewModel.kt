package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.domain.interactors.currency.CalculateCurrencies
import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.domain.interactors.currency.MoveSelectedElementToFirstPosition
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import com.example.mvrxexample.ui.base.BaseViewModel
import com.example.mvrxexample.utils.rx.Transformer
import com.example.mvrxexample.utils.rx.applySchedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel(
    initialState: HomeState,
    private val getCurrencyFromRemote: GetCurrencyFromRemote,
    private val moveSelectedElementToFirstPosition: MoveSelectedElementToFirstPosition,
    private val calculateCurrencies: CalculateCurrencies,
    private val rxTransformer: Transformer
) : BaseViewModel<HomeState>(initialState) {

    init {
        preLoadCurrency()
    }

    private fun preLoadCurrency() {
        getCurrencyFromRemote()
            .applySchedulers(rxTransformer)
            .execute { copy(currency = it) }
    }

    fun renderListInCorrectOrder(rate: Rate, currency: Currency) {
        moveSelectedElementToFirstPosition(rate, currency)
            .applySchedulers(rxTransformer)
            .execute { copy(currency = it) }
    }

    fun showRatesValue(value: String, currency: Currency) {
        if (value.isNotEmpty())
            calculateCurrencies(value.toDouble(), currency)
                .applySchedulers(rxTransformer)
                .execute { copy(currency = it) }
    }

    companion object : MvRxViewModelFactory<HomeViewModel, HomeState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: HomeState
        ): HomeViewModel? {
            val viewModel: HomeViewModel by viewModelContext.activity.inject {
                parametersOf(state)
            }
            return viewModel
        }
    }
}