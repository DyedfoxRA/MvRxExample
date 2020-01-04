package com.example.mvrxexample.ui.settings

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.mvrxexample.R
import com.example.mvrxexample.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    private val settingsViewModel: SettingsViewModel by fragmentViewModel()

    override val layoutId = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsViewModel.fetchNotificationsSettings()

        switcher.setOnClickListener {
            settingsViewModel.changeNotificationSettings(switcher.isChecked)
        }
    }

    override fun invalidate() = withState(settingsViewModel) { state ->
        if (state.isSwitcherEnabled != null) {
            switcher.isChecked = state.isSwitcherEnabled
            switcher.visibility = View.VISIBLE
        }
    }
}