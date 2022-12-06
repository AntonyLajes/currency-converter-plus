package com.example.currency_converter_plus.model.remote

import com.example.currency_converter_plus.util.models.ConvertModel
import com.example.currency_converter_plus.util.models.ConvertedCurrencyModel
import com.example.currency_converter_plus.util.models.CurrencyTypeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyConverterEndpoint {

    @GET("api/v1/supportedCurrencies")
    fun getSupportedCurrencies(): Call<List<CurrencyTypeModel>>

    @GET("convert")
    fun getCurrencyConverter(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): Call<ConvertedCurrencyModel>

}