package com.example.mvrxexample.domain.model

data class Currency(
    val name: String = "No name",
    val rates: List<Rate> = emptyList(),
    val date: String = " No date"
)