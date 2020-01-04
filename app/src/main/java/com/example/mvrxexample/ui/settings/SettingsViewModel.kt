package com.example.mvrxexample.ui.settings

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.example.mvrxexample.domain.interactors.settings.GetSwitchSettings
import com.example.mvrxexample.domain.interactors.settings.SetSwitchSettings
import com.example.mvrxexample.ui.base.BaseViewModel
import com.example.mvrxexample.utils.rx.Transformer
import com.example.mvrxexample.utils.rx.applySchedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingsViewModel(
    initialState: SettingsState,
    private val getSwitchSettings: GetSwitchSettings,
    private val setSwitchSettings: SetSwitchSettings,
    private val rxTransformer: Transformer
) : BaseViewModel<SettingsState>(initialState) {

    fun fetchNotificationsSettings() {
        getSwitchSettings()
            .applySchedulers(rxTransformer)
            .execute { copy(isSwitcherEnabled = it()) }
    }

    fun changeNotificationSettings(isChecked: Boolean) {
        setSwitchSettings(isChecked)
            .applySchedulers(rxTransformer)
            .execute { copy(isSwitcherEnabled = isChecked) }
    }

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