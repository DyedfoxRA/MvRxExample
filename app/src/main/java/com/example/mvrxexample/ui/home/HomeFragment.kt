package com.example.mvrxexample.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.mvrxexample.R
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate
import com.example.mvrxexample.ui.base.BaseEpoxyFragment
import com.example.mvrxexample.ui.base.MvRxEpoxyController
import com.example.mvrxexample.ui.base.simpleController
import com.example.mvrxexample.ui.home.views.currencyRow
import com.example.mvrxexample.ui.home.views.headerRow
import com.example.mvrxexample.ui.home.views.rateRow
import com.example.mvrxexample.utils.exeptions.doOnMotionEventDetected
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseEpoxyFragment() {

    override val layoutId = R.layout.fragment_home
    private val homeViewModel: HomeViewModel by fragmentViewModel()
    private val inputMethodManager: InputMethodManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        withState(homeViewModel) { state ->
            homeViewModel.selectCurrency(state.currency()?.rates?.first() ?: Rate())
        }
    }

    override fun invalidate() = withState(homeViewModel) { state ->
        super.invalidate()
        if (state.selectedCurrency is Success)
            currency_main.text = state.selectedCurrency()?.name
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
                id(rate.name)
                name(rate.name)
                value(rate.number.toString())
                clickListener { _ ->
                    homeViewModel.selectCurrency(rate)
                }
            }
        }
    }

    private fun initListeners() {

        cancel.setOnClickListener {
            homeViewModel.cancelSearch()
            enter_value.text.clear()
            homeViewModel.calculateCurrency("1")
        }

        enter_value.doOnTextChanged { text, _, _, _ ->
            homeViewModel.calculateCurrency(text.toString())
        }

        recyclerView.doOnMotionEventDetected {
            hideSoftKeyboard()
        }
    }

    private fun hideSoftKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(enter_value.windowToken, 0)
    }
}