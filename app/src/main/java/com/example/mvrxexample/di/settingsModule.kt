package com.example.mvrxexample.di

import com.example.mvrxexample.domain.interactors.settings.GetSwitchSettings
import com.example.mvrxexample.domain.interactors.settings.SetSwitchSettings
import com.example.mvrxexample.ui.settings.SettingsState
import com.example.mvrxexample.ui.settings.SettingsViewModel
import org.koin.dsl.module

internal val settingsModule = module {

    factory { GetSwitchSettings(settingsRepository = get()) }
    factory { SetSwitchSettings(settingsRepository = get()) }

    factory { (state: SettingsState) ->
        SettingsViewModel(
            state,
            getSwitchSettings = get(),
            setSwitchSettings = get(),
            rxTransformer = get()
        )
    }
}