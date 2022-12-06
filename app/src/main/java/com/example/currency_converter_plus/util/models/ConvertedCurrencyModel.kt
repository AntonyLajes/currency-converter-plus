package com.example.currency_converter_plus.util.models

import com.google.gson.annotations.SerializedName

data class ConvertedCurrencyModel (
    @SerializedName("request")
    val request: RequestModel,
    @SerializedName("result")
    val result: Double
    )