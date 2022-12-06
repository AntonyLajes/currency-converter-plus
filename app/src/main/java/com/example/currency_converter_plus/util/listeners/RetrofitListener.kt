package com.example.currency_converter_plus.util.listeners

import com.example.currency_converter_plus.util.models.CurrencyTypeModel
import retrofit2.Response

interface RetrofitListener<T> {

    fun onSuccess(response: T)

    fun onError(message: String)
}