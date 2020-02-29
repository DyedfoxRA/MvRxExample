package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.ui.base.BaseViewModel
import com.example.mvrxexample.utils.rx.Transformer
import com.example.mvrxexample.utils.rx.applySchedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel(
    initialState: HomeState,
    private val getCurrencyFromRemote: GetCurrencyFromRemote,
    private val rxTransformer: Transformer
) : BaseViewModel<HomeState>(initialState) {

    init {
        preLoadCurrency()
    }

    fun preLoadCurrency() {
        getCurrencyFromRemote()
            .applySchedulers(rxTransformer)
            .execute { copy(currency = it()) }
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