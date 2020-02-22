package com.example.mvrxexample.domain.interactors.settings

import com.example.mvrxexample.data.settings.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class GetSwitchSettingsTest {

    val settingsRepository: SettingsRepository = mockk()
    val getSwitchSettings = GetSwitchSettings(settingsRepository)

    @Test
    fun `getSwitchSetting returns correct value`() {
        every { settingsRepository.getSwitchState() } returns Single.just(true)
        getSwitchSettings().test().assertValue(true)
    }

    @Test
    fun `getSwitchSetting on fail returns correct value`() {
        every { settingsRepository.getSwitchState() } returns Single.error(Throwable())
        getSwitchSettings().test().assertValue(true)
    }
}