package com.example.currency_converter_plus.util.models

import com.google.gson.annotations.SerializedName

data class ConvertModel (
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("amountToConvert")
    val amountToConvert: Double,
    @SerializedName("convertedAmount")
    val convertedAmount: Double
    )