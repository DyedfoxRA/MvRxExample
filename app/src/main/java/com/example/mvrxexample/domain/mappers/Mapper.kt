package com.example.mvrxexample.domain.mappers

interface Mapper<From, To> {
    fun map(raw: From): To
}