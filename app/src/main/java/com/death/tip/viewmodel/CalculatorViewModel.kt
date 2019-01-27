package com.death.tip.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import com.death.tip.model.Calculator
import com.death.tip.model.TipCalculation

class CalculatorViewModel(private val calculator:Calculator = Calculator()) : BaseObservable() {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    fun calculateTip(){

        Log.e("ViewModel", "calculateTipInvoked")

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount!=null && tipPct!=null){
            Log.e("ViewModel","CheckAmount: $checkAmount, TipPercentage: $tipPct")
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
            clearInputs()
        }

    }

    private fun clearInputs() {
        inputTipPercentage = ""
        inputCheckAmount = ""
        notifyChange()
    }

}