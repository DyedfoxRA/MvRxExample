package com.example.mvrxexample.ui.home

import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.mvrxexample.R
import com.example.mvrxexample.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_home
    private val homeViewModel: HomeViewModel by fragmentViewModel()

    override fun invalidate() = withState(homeViewModel) { state ->
        println("AAA ${state.currency}")
    }
}