package com.death.tip.viewmodel

import com.death.tip.model.Calculator
import com.death.tip.model.TipCalculation

class CalculatorViewModel(private val calculator:Calculator = Calculator()) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var tipCalculation = TipCalculation()

    fun calculateTip(){
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount!=null && tipPct!=null){
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
        }
    }

}