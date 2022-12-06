package com.example.currency_converter_plus.ui.viewmodel

import android.app.Application
import android.text.method.LinkMovementMethod
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_converter_plus.R
import com.example.currency_converter_plus.model.remote.CurrencyConverterData
import com.example.currency_converter_plus.util.listeners.RetrofitListener
import com.example.currency_converter_plus.util.models.CurrencyTypeModel
import retrofit2.Response

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val currencyConverterData = CurrencyConverterData(application.applicationContext)
    private var _supportedCurrencies: MutableLiveData<List<String>> = MutableLiveData()
    val supportedCurrencies: LiveData<List<String>> get() = _supportedCurrencies

    fun getSupportedCurrencies(){
        val getSupportedCurrencies: MutableList<String> = mutableListOf()
        currencyConverterData.getSupportedCurrencies(object : RetrofitListener<List<CurrencyTypeModel>>{
            override fun onSuccess(response: List<CurrencyTypeModel>) {
                for(currencies in response){
                    getSupportedCurrencies.add(currencies.symbol)
                }
                _supportedCurrencies.value = getSupportedCurrencies
            }

            override fun onError(message: String) {
                val s = message
            }

        })
    }

}