package com.example.mvrxexample.di

import com.example.mvrxexample.domain.mappers.currency.CurrencyResponseToCurrencyMapper
import com.example.mvrxexample.domain.mappers.currency.CurrencyResponseToCurrencyMapperImpl
import org.koin.dsl.module

internal val mapperModule = module {

    factory<CurrencyResponseToCurrencyMapper> { CurrencyResponseToCurrencyMapperImpl() }
}