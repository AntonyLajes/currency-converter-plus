package com.example.currency_converter_plus.util.constants

import com.example.currency_converter_plus.util.models.HeaderModel

class RetrofitConstants private constructor(){

    companion object{
        val BASE_URL_CC: String = "https://currency-converter18.p.rapidapi.com/"
        val BASE_URL_CCPRO: String = "https://currency-converter-pro1.p.rapidapi.com/"
        val RAPID_API_KEY = HeaderModel("X-RapidAPI-Key", "7ea476117fmsh8f9bc9c1d47b127p109bdcjsne35f515fc219")
        val RAPID_API_HOST = HeaderModel("X-RapidAPI-Host", "currency-converter18.p.rapidapi.com")
        val RAPID_API_HOST_CCPRO = HeaderModel("X-RapidAPI-Host", "currency-converter-pro1.p.rapidapi.com")
        const val SUCCESS = 200
        const val SUPPORTED_CURRIENCIES_SYMBOLS = "SupportedCurrenciesSymbols"
    }

}