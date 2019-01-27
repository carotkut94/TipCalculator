package com.death.tip.model

import java.math.RoundingMode

class Calculator{
    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation{

        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
            .toBigDecimal()
            .setScale(2,RoundingMode.HALF_UP)
            .toDouble()

        val grandTotal = checkAmount + tipAmount
        return  TipCalculation(
            checkAmount = checkAmount,
            grandTotal = grandTotal,
            tipPct = tipPct,
            tipAmount = tipAmount
        )
    }

    fun saveTipCalculation(tipToSave: TipCalculation) {
        val repository = TipCalculationRepository()
        repository.saveTipCalculation(tipToSave)
    }
}