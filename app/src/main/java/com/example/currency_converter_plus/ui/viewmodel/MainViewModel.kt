package com.example.currency_converter_plus.ui.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currency_converter_plus.databinding.ActivityMainBinding
import com.example.currency_converter_plus.model.remote.CurrencyConverterData
import com.example.currency_converter_plus.util.listeners.RetrofitListener
import com.example.currency_converter_plus.util.models.ConvertModel
import com.example.currency_converter_plus.util.models.ConvertedCurrencyModel
import com.example.currency_converter_plus.util.models.CurrencyTypeModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyConverterData = CurrencyConverterData(application.applicationContext)
    private var _convertedAmount: MutableLiveData<String> = MutableLiveData()
    val convertedAmount: LiveData<String> get() = _convertedAmount

    fun getCurrencyConverter(
        from: String,
        to: String,
        amount: String,
    ) {
        currencyConverterData.getCurrencyConverter(from, to, amount, object : RetrofitListener<ConvertedCurrencyModel> {
            override fun onSuccess(response: ConvertedCurrencyModel) {
                val result = response.result
                _convertedAmount.value = String.format("%.2f", result)
            }

            override fun onError(message: String) {
                val s = message
            }

        })
    }
}