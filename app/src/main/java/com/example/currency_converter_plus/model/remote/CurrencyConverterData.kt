package com.example.currency_converter_plus.model.remote

import android.content.Context
import com.example.currency_converter_plus.util.constants.RetrofitConstants
import com.example.currency_converter_plus.util.listeners.RetrofitListener
import com.example.currency_converter_plus.util.models.ConvertModel
import com.example.currency_converter_plus.util.models.ConvertedCurrencyModel
import com.example.currency_converter_plus.util.models.CurrencyTypeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path

class CurrencyConverterData(context: Context) {

    private val retrofitClient = RetrofitClient.getRetrofit()
    private val endpointCC = retrofitClient.create(CurrencyConverterEndpoint::class.java)

    private val retrofitClientCcPro = RetrofitClientCcPro.getRetrofit()
    private val endpointCcPro = retrofitClientCcPro.create(CurrencyConverterEndpoint::class.java)

    fun getSupportedCurrencies(listener: RetrofitListener<List<CurrencyTypeModel>>) {
        val getSupportedCurrenciesCallback = endpointCC.getSupportedCurrencies()
        getSupportedCurrenciesCallback.enqueue(object : Callback<List<CurrencyTypeModel>> {
            override fun onResponse(
                call: Call<List<CurrencyTypeModel>>,
                response: Response<List<CurrencyTypeModel>>
            ) {
                if (response.code() == RetrofitConstants.SUCCESS) {
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<CurrencyTypeModel>>, t: Throwable) {
                listener.onError(t.message ?: "Erro.")
            }

        })
    }


    fun getCurrencyConverter(from: String, to: String, amount: String, listener: RetrofitListener<ConvertedCurrencyModel>){
        val getCurrencyConverterCallback = endpointCcPro.getCurrencyConverter(from, to, amount)
        getCurrencyConverterCallback.enqueue(object : Callback<ConvertedCurrencyModel>{
            override fun onResponse(
                call: Call<ConvertedCurrencyModel>,
                response: Response<ConvertedCurrencyModel>
            ) {
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ConvertedCurrencyModel>, t: Throwable) {
                val s = t
            }

        })
    }

}