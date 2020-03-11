package com.example.mvrxexample.ui.home

import com.example.mvrxexample.domain.interactors.currency.GetCurrencyFromRemote
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import com.example.mvrxexample.robot.home.homeFragment
import com.example.mvrxexample.utils.rx.TestTransformer
import com.example.mvrxexample.utils.rx.Transformer
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.mock.declare

class HomeFragmentTest : KoinTest {

    val getCurrencyFromRemote: GetCurrencyFromRemote = mockk(relaxed = true)

    @Before
    fun setUp() {
        declare {
            single { getCurrencyFromRemote() }
            single<Transformer> { TestTransformer() }
        }
    }

    @Test
    fun layout_id_is_equals_fragment_radio() {
        homeFragment {
            startWithDefaultBackground()
            hasProperLayout()
        }
    }

    @Test
    fun invalidate_currency_main_text() {
        homeFragment {
            startWithDefaultBackground()
            isSelectedCurrencyDisplayed()
        }
    }

    @Test
    fun invalidate_shows_edit_text() {
        homeFragment {
            startWithDefaultBackground()
            isEditTextDisplayed()
        }
    }

    @Test
    fun invalidate_shows_cancel_text() {
        homeFragment {
            startWithDefaultBackground()
            isCancelDisplayed()
        }
    }

    @Test
    fun invalidate_edit_text_match_its_hint() {
        homeFragment {
            startWithDefaultBackground()
            matchTextHintForSearchEditText("Enter value")
        }
    }

    @Test
    fun invalidate_rate_row_displayed() {
        every { getCurrencyFromRemote() } returns Single.just(
            Currency(
                rates = listOf(
                    Rate(
                        "USD",
                        1.0,
                        1.0
                    )
                )
            )
        )

        homeFragment {
            startWithDefaultBackground()
            isDisplayed("USD")
        }
    }
}