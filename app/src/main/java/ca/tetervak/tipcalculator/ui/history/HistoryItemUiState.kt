package ca.tetervak.tipcalculator.ui.history

import java.util.*

data class HistoryItemUiState(
    val id: Int,
    val date: Date,
    val tipAmount: Double,
    val billTotal: Double
)
