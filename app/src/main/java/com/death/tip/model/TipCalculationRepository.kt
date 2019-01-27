package com.death.tip.model

class TipCalculationRepository {
    private val saveTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation){
        saveTips[tc.locationName] = tc
    }

    fun loadTipCalculationByName(locationName: String) : TipCalculation?{
        return saveTips[locationName]
    }
}