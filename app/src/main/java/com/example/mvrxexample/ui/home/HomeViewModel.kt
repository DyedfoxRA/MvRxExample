package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.domain.interactors.currency.CalculateCurrencies
import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.domain.model.Rate
import com.example.mvrxexample.ui.base.BaseViewModel
import com.example.mvrxexample.utils.rx.Transformer
import com.example.mvrxexample.utils.rx.applySchedulers
import io.reactivex.Single
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel(
    initialState: HomeState,
    private val getCurrencyFromRemote: GetCurrencyFromRemote,
    private val calculateCurrencies: CalculateCurrencies,
    private val rxTransformer: Transformer
) : BaseViewModel<HomeState>(initialState) {

    init {
        preLoadCurrency()
    }

    fun preLoadCurrency() {
        getCurrencyFromRemote()
            .applySchedulers(rxTransformer)
            .execute { copy(currency = it) }
    }

    fun selectCurrency(rate: Rate) {
        Single.just(rate)
            .applySchedulers(rxTransformer)
            .execute { copy(selectedCurrency = it) }
    }

    fun calculateCurrency(number: String) {
        withState { state ->
            if (number.isNotEmpty())
                calculateCurrencies(number.toDouble(), state.selectedCurrency() ?: Rate())
                    .applySchedulers(rxTransformer)
                    .execute { copy(currency = it, writtenNumber = number) }
        }
    }

    fun cancelSearch() {
        setState { copy(writtenNumber = "1") }
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