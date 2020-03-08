package com.example.mvrxexample.ui.home.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.mvrxexample.R
import kotlinx.android.synthetic.main.placeholder_rate_row.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RateRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.placeholder_rate_row, this)
    }

    @TextProp
    fun name(title: CharSequence) {
        rate_name.text = title
    }

    @TextProp
    fun value(value: CharSequence) {
        rate_value.setText(value)
    }

    @CallbackProp
    fun number1(onNum: ((String) -> Unit)?) = rate_value.text

    @CallbackProp
    fun setOnChange(onChange: ((String) -> Unit)?) {
        rate_value.doOnTextChanged { text, start, count, after -> onChange?.invoke(text.toString()) }
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}