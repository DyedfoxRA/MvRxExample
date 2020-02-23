package com.example.mvrxexample.data.response

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("base") val name: String,
    @SerializedName("rates") val data: Map<String, Double>,
    @SerializedName("date") val date: String
)