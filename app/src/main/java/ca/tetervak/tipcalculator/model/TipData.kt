package ca.tetervak.tipcalculator.model

import java.util.*

data class TipData (
    val id: Int = 0,
    val costOfService: Double,
    val serviceQuality: ServiceQuality,
    val roundUpTip: Boolean,
    val tipAmount: Double,
    val billTotal: Double,
    val date: Date = Date()
)
