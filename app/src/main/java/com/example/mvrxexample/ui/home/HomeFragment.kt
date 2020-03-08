package com.example.mvrxexample.ui.home

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.example.mvrxexample.R
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.ui.base.BaseEpoxyFragment
import com.example.mvrxexample.ui.base.MvRxEpoxyController
import com.example.mvrxexample.ui.base.simpleController
import com.example.mvrxexample.ui.home.views.currencyRow
import com.example.mvrxexample.ui.home.views.headerRow
import com.example.mvrxexample.ui.home.views.rateRow
import kotlinx.android.synthetic.main.placeholder_rate_row.*

class HomeFragment : BaseEpoxyFragment() {

    override val layoutId = R.layout.fragment_home
    private val homeViewModel: HomeViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun epoxyController(): MvRxEpoxyController =
        simpleController(homeViewModel) { state ->
            if (state.currency !is Success)
                return@simpleController

            renderRateRow(state.currency())
        }

    private fun EpoxyController.renderCurrencyRow(currency: Currency?) {
        currencyRow {
            id("${currency?.name}")
            name(currency?.name ?: "No name")
            date(currency?.date ?: "No date")
        }
    }

    private fun EpoxyController.renderHeaderRow() {
        headerRow {
            id("header")
        }
    }

    private fun EpoxyController.renderRateRow(currency: Currency?) {
        currency?.rates?.forEach { rate ->
            rateRow {
                id("${rate.name}")
                name(rate.name)
                value((rate.number * rate.value).toString())
                clickListener { _ ->
                    homeViewModel.renderListInCorrectOrder(rate, currency)
                }
                println("AAA ${rate.number}")

//                onChange {
//                    homeViewModel.showRatesValue(
//                        rate.,
//                        currency
//                    )
//                }
//                onChange { homeViewModel.showRatesValue() }
//                try {
//
//                    onChange {
//                        homeViewModel.showRatesValue(
//                            requireActivity().rate_value.text.toString(),
//                            currency
//                        )
//                    }
//                } catch (e: Exception) {
//                }
            }
        }
    }
}