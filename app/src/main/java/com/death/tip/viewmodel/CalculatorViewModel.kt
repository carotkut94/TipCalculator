package com.death.tip.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.death.tip.R
import com.death.tip.model.Calculator
import com.death.tip.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
    app: Application,
    private val calculator: Calculator = Calculator()
) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()
    var inputCheckAmount = ""
    var inputTipPercentage = ""

    //var tipCalculation = TipCalculation()

    val outputCheckAmount: String
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculated.checkAmount
        )

    val outputTipAmount: String
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculated.tipAmount
        )

    val outputTotalAmount: String
        get() = getApplication<Application>().getString(
            R.string.dollar_amount,
            lastTipCalculated.grandTotal
        )

    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
        notifyChange()
    }

    fun calculateTip() {

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

    fun loadSavedTipCalculationSummaries(): LiveData<List<TipCalculationSummaryItem>> {
        return Transformations.map(calculator.loadSavedTipCalculations()) { tipCalculationObjects ->
            tipCalculationObjects.map {
                TipCalculationSummaryItem(
                    it.locationName,
                    getApplication<Application>().getString(R.string.dollar_amount, it.grandTotal)
                )
            }
        }
    }

    fun loadTipCalculation(name: String) {
        val tc = calculator.loadTipCalculationByLocationName(name)
        if (tc != null) {
            inputCheckAmount = tc.checkAmount.toString()
            inputTipPercentage = tc.tipPct.toString()

            updateOutputs(tc)
            notifyChange()
        }
    }

}