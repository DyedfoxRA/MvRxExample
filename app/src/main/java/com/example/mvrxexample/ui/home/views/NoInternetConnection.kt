package com.example.mvrxexample.ui.home.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelView
import com.example.mvrxexample.R

@ModelView(defaultLayout = R.layout.placeholder_no_internet_conection)
class NoInternetConnection @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr)