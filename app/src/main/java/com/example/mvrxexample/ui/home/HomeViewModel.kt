package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.ui.base.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel(
    initialState: HomeState
) : BaseViewModel<HomeState>(initialState) {

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