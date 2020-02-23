package com.example.mvrxexample.utils.exeptions

import com.example.mvrxexample.R
import java.io.IOException

class NoInternetException(throwable: Throwable) : BaseToastException(throwable) {
    override val userMessageResourceId: Int = R.string.error_internet_disconnected
}

fun Throwable.mapToNoInternetException() =
    if (this is IOException) NoInternetException(this) else this