package com.example.currency_converter_plus.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.currency_converter_plus.R
import com.example.currency_converter_plus.databinding.ActivitySplashBinding
import com.example.currency_converter_plus.ui.viewmodel.SplashViewModel
import com.example.currency_converter_plus.util.constants.RetrofitConstants

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        getData()
        initLink()
    }

    private fun getData(){
        viewModel.getSupportedCurrencies()
        observers()
    }

    private fun observers(){
        viewModel.supportedCurrencies.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(RetrofitConstants.SUPPORTED_CURRIENCIES_SYMBOLS, ArrayList(it))
            startActivity(intent)
        }
    }

    private fun initLink(){
        binding.tvLink.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLink.setLinkTextColor(getColor(R.color.green_secondary))
    }
}