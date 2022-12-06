package com.example.currency_converter_plus.util.models

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("amount")
    val amount: Double
)