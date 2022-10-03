package ca.tetervak.tipcalculator.ui.history

import java.util.*

data class HistoryItemUiState(
    val date: Date,
    val tipAmount: Double,
    val billTotal: Double,
    val onDelete: () -> Unit
)
