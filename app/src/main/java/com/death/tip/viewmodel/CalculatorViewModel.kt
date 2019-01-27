package com.death.tip.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import com.death.tip.R
import com.death.tip.model.Calculator
import com.death.tip.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app: Application, private val calculator:Calculator = Calculator()) : ObservableViewModel(app) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    //var tipCalculation = TipCalculation()

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation){
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip(){

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount!=null && tipPct!=null){
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            notifyChange()
        }
    }

}