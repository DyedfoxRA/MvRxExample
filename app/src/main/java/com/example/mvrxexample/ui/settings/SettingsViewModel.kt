package com.example.mvrxexample.ui.settings

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.ui.base.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingsViewModel(
    initialState: SettingsState
) : BaseViewModel<SettingsState>(initialState) {

    companion object : MvRxViewModelFactory<SettingsViewModel, SettingsState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: SettingsState
        ): SettingsViewModel? {
            val viewModel: SettingsViewModel by viewModelContext.activity.inject {
                parametersOf(state)
            }
            return viewModel
        }
    }
}