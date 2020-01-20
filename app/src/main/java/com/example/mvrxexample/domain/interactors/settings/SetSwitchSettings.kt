package com.example.mvrxexample.domain.interactors.settings

import com.example.mvrxexample.data.settings.SettingsRepository
import io.reactivex.Completable

class SetSwitchSettings(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke(isChecked: Boolean): Completable =
        settingsRepository.saveSwitchState(isChecked)
}