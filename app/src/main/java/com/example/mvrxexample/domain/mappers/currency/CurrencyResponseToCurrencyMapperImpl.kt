package com.example.mvrxexample.domain.mappers.currency

import com.example.mvrxexample.data.response.CurrencyResponse
import com.example.mvrxexample.domain.mappers.Mapper
import com.example.mvrxexample.domain.model.Currency
import com.example.mvrxexample.domain.model.Rate

class CurrencyResponseToCurrencyMapperImpl : CurrencyResponseToCurrencyMapper {

    override fun map(raw: CurrencyResponse): Currency =
        Currency(raw.name, raw.data.map { Rate(it.key, it.value) }, raw.date)
}

interface CurrencyResponseToCurrencyMapper : Mapper<CurrencyResponse, Currency>