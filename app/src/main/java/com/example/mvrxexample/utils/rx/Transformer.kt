package com.example.mvrxexample.utils.rx

import io.reactivex.Scheduler

interface Transformer {

    val mainScheduler: Scheduler
    val ioScheduler: Scheduler
    val computationScheduler: Scheduler
}