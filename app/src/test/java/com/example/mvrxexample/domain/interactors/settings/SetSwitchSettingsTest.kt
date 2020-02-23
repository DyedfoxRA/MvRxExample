package com.example.mvrxexample.domain.interactors.settings

import com.example.mvrxexample.data.settings.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import org.junit.Test

class SetSwitchSettingsTest {

    val settingsRepository: SettingsRepository = mockk()
    val setSwitchSettings = SetSwitchSettings(settingsRepository)

    @Test
    fun `getSwitchSetting returns correct value`() {
        val isCheck = true
        every { settingsRepository.saveSwitchState(isCheck) } returns Completable.complete()
        setSwitchSettings(isCheck).test().assertComplete()
    }

    @Test
    fun `getSwitchSetting on fail returns correct value`() {
        val throwable = Throwable()
        every { settingsRepository.saveSwitchState(true) } returns Completable.error(throwable)
        setSwitchSettings(true).test().assertError(throwable)
    }
}