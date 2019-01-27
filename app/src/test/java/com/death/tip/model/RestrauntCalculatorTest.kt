package com.death.tip.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestrauntCalculatorTest {

    lateinit var calculator: RestrauntCalculator

    @Before
    fun setup() {
        calculator = RestrauntCalculator()
    }

    @Test
    fun testCalculateTip() {
        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVals = listOf(
            baseTc.copy(tipAmount = 2.5, tipPct = 25, grandTotal = 12.50),
            baseTc.copy(tipAmount = 1.5, tipPct = 15, grandTotal = 11.50),
            baseTc.copy(tipAmount = 1.8, tipPct = 18, grandTotal = 11.80)
        )

        testVals.forEach {
            assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPct))
        }
    }
}