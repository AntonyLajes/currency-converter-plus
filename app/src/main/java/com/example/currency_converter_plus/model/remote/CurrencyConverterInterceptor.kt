package com.example.currency_converter_plus.model.remote

import com.example.currency_converter_plus.util.constants.RetrofitConstants
import okhttp3.Interceptor
import okhttp3.Response

class CurrencyConverterInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(RetrofitConstants.RAPID_API_KEY.name, RetrofitConstants.RAPID_API_KEY.value)
            .addHeader(RetrofitConstants.RAPID_API_HOST.name, RetrofitConstants.RAPID_API_HOST.value)
            .build()
        return chain.proceed(request)
    }
}