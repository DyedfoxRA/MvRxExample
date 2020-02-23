package com.example.mvrxexample.utils.exeptions

abstract class BaseToastException : Exception {

    abstract val userMessageResourceId: Int

    constructor() : super()
    constructor(throwable: Throwable) : super(throwable)
}