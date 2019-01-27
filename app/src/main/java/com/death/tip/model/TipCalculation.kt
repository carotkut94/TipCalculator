package com.death.tip.model

data class TipCalculation (
    var checkAmount: Double = 0.0,
    val tipPct: Int = 0,
    val tipAmount: Double = 0.0,
    val grandTotal: Double = 0.0
)