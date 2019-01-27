package com.death.tip.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import com.death.tip.R
import com.death.tip.model.Calculator
import com.death.tip.model.TipCalculation

class CalculatorViewModel(val app: Application, private val calculator:Calculator = Calculator()) : BaseObservable() {

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
        outputCheckAmount = app.getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalAmount = app.getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip(){

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount!=null && tipPct!=null){
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }

    }

    private fun clearInputs() {
        inputTipPercentage = ""
        inputCheckAmount = ""
        notifyChange()
    }

}