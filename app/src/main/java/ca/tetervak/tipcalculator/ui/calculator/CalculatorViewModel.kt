package ca.tetervak.tipcalculator.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator.model.ServiceQuality
import ca.tetervak.tipcalculator.model.TipCalculator
import ca.tetervak.tipcalculator.model.TipData

class CalculatorViewModel() : ViewModel() {

    val tipCalculator = TipCalculator()

    private val _liveUiState = MutableLiveData(CalculatorUiState())
    val liveUiState: LiveData<CalculatorUiState> = _liveUiState

    private var _tipData: TipData? = null
    val tipData: TipData
        get() = _tipData!!

    fun calculate(
        costOfService: Double,
        serviceQuality: ServiceQuality,
        roundUpTip: Boolean
    ) {
        val tipData = tipCalculator.calculate(costOfService, serviceQuality, roundUpTip)
        val uiState = _liveUiState.value
        _liveUiState.value = uiState?.copy(
            tipAmount = tipData.tipAmount,
            billTotal = tipData.billTotal
        )
        _tipData = tipData
    }
}