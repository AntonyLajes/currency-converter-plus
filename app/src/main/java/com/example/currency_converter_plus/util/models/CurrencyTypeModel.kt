package com.example.currency_converter_plus.util.models

import com.google.gson.annotations.SerializedName

data class CurrencyTypeModel(
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String
)