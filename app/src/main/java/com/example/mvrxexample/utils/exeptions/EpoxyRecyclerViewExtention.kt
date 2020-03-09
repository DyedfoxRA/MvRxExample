package com.example.mvrxexample.utils.exeptions

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.doOnMotionEventDetected(hideSoftKeyboard: () -> Unit) {
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_MOVE)
            hideSoftKeyboard()
        false
    }
}