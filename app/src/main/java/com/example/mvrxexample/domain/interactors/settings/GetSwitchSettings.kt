package com.example.mvrxexample.domain.interactors.settings

import com.example.mvrxexample.data.settings.SettingsRepository
import io.reactivex.Single

class GetSwitchSettings(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke(): Single<Boolean> =
        settingsRepository.getSwitchState()
            .onErrorReturn { true }
}