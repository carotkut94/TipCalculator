package com.death.tip.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculatorRepositoryTest {

    /**
     * As we can not directly test code related to LiveData as it depends
     * upon the Looper which is available only in android
     * we need to use androidx.arch.core:core-testing in order to execute them using
     * the below rule.
     */
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

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

        val tip2 = TipCalculation(locationName = "Jodhpur",
            checkAmount = 100.0,
            tipAmount = 25.0,
            tipPct = 25,
            grandTotal = 125.0
        )

        repository.saveTipCalculation(tip)
        repository.saveTipCalculation(tip2)
        //assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))

        repository.loadSavedTipCalculation().observeForever{
            tipCalculations ->
            assertEquals(2, tipCalculations?.size)
        }
    }
}