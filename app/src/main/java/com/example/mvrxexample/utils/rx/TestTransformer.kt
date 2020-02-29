package com.example.mvrxexample.utils.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestTransformer(
    private val main: Scheduler = Schedulers.trampoline(),
    private val io: Scheduler = Schedulers.trampoline(),
    private val computation: Scheduler = Schedulers.trampoline()
) : Transformer {

    override val mainScheduler: Scheduler
        get() = main
    override val ioScheduler: Scheduler
        get() = io
    override val computationScheduler: Scheduler
        get() = computation
}