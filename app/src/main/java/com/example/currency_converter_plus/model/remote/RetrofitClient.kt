package com.example.currency_converter_plus.model.remote

import com.example.currency_converter_plus.util.constants.RetrofitConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object{
        private lateinit var INSTANCE: Retrofit
        private val client = OkHttpClient.Builder().apply {
            addInterceptor(CurrencyConverterInterceptor())
        }.build()

        fun getRetrofit(): Retrofit{
            if(!::INSTANCE.isInitialized){
                INSTANCE = Retrofit.Builder()
                    .baseUrl(RetrofitConstants.BASE_URL_CC)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }
    }

}