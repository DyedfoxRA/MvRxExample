package com.example.mvrxexample.data.settings

import io.reactivex.Completable
import io.reactivex.Single

interface SettingsRepository {

    fun saveSwitchState(isChecked: Boolean): Completable
    fun getSwitchState(): Single<Boolean>
}