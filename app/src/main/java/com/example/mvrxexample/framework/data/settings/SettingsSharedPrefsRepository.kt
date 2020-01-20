package com.example.mvrxexample.framework.data.settings

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.mvrxexample.data.settings.SettingsRepository
import io.reactivex.Completable
import io.reactivex.Single

class SettingsSharedPrefsRepository(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    companion object {
        private const val KEY_SETTINGS_SWITCH_ENABLED = "SETTINGS_SWITCH_ENABLED"
    }

    @SuppressLint("ApplySharedPref")
    override fun saveSwitchState(isChecked: Boolean): Completable = Completable.fromAction {
        sharedPreferences.edit().putBoolean(KEY_SETTINGS_SWITCH_ENABLED, isChecked).commit()
    }

    override fun getSwitchState(): Single<Boolean> = Single.fromCallable {
        sharedPreferences.getBoolean(KEY_SETTINGS_SWITCH_ENABLED, true)
    }
}