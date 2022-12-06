package com.example.currency_converter_plus.ui.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.currency_converter_plus.R
import com.example.currency_converter_plus.databinding.ActivityMainBinding
import com.example.currency_converter_plus.ui.viewmodel.MainViewModel
import com.example.currency_converter_plus.util.constants.RetrofitConstants
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var supportedCurrenciesSymbols: ArrayList<String> = arrayListOf()
    private var to = ""
    private var from = ""
    private var amount = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        supportedCurrenciesSymbols = intent.getStringArrayListExtra(RetrofitConstants.SUPPORTED_CURRIENCIES_SYMBOLS) as ArrayList<String>
        initSpinners()
        initClicks()
        initLink()
        handlerAmount()
        observers()
    }

    override fun onClick(view: View) {
        when(view.id){
            binding.contentMain.swapIcon.id ->{
                val toPosition = binding.contentMain.inputFrom.selectedItemPosition
                val fromPosition = binding.contentMain.inputTo.selectedItemPosition

                initSpinners()

                binding.contentMain.inputFrom.setSelection(fromPosition, true)
                binding.contentMain.inputTo.setSelection(toPosition, true)
            }
        }
    }

    private fun initSpinners(){
        if(supportedCurrenciesSymbols.isNotEmpty()){
            binding.contentMain.inputFrom.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, supportedCurrenciesSymbols.toList())
            binding.contentMain.inputTo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, supportedCurrenciesSymbols.toList())
        }
    }

    private fun initLink(){
        binding.tvLink.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLink.setLinkTextColor(getColor(R.color.green_secondary))
    }

    private fun initClicks(){
        binding.contentMain.swapIcon.setOnClickListener(this)
    }

    private fun handlerAmount(){
        binding.contentMain.etInputAmount.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val s = ""
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                if(!charSequence.isNullOrEmpty()){
                    val tvTypeInputAmountText = "$charSequence ${binding.contentMain.inputFrom.selectedItem} ="
                    binding.contentMain.tvTypeInputAmount.text = tvTypeInputAmountText
                    getData()
                }else{
                    binding.contentMain.tvTypeInputAmount.text = ""
                    binding.contentMain.tvInputAmount.text = ""
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                val s = ""
            }

        })

        binding.contentMain.inputFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                getData()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                val s = ""
            }
        }
        binding.contentMain.inputTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
               getData()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                val s = ""
            }
        }

    }

    private fun observers(){
        viewModel.convertedAmount.observe(this){
            val tvInputAmountText = "$it ${binding.contentMain.inputTo.selectedItem}"
            binding.contentMain.tvInputAmount.text = tvInputAmountText
        }
    }

    private fun getData(){
        val tvTypeInputAmountText = "${binding.contentMain.etInputAmount.text} ${binding.contentMain.inputFrom.selectedItem} ="
        binding.contentMain.tvTypeInputAmount.text = tvTypeInputAmountText
        to = binding.contentMain.inputFrom.selectedItem.toString()
        from = binding.contentMain.inputTo.selectedItem.toString()
        amount = binding.contentMain.etInputAmount.text.toString()
        viewModel.getCurrencyConverter(to, from, amount)
    }
}