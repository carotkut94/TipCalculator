package com.death.tip.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TipCalculatorRepositoryTest {

    private lateinit var repository: TipCalculationRepository

    @Before
    fun setup(){
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip(){
        val tip = TipCalculation(locationName = "Jaipur",
            checkAmount = 100.0,
            tipAmount = 25.0,
            tipPct = 25,
            grandTotal = 125.0
            )

        repository.saveTipCalculation(tip)
        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }
}