package ca.tetervak.tipcalculator.ui.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.tipcalculator.data.TipDataRepository
import ca.tetervak.tipcalculator.model.ServiceQuality
import ca.tetervak.tipcalculator.model.TipCalculator
import ca.tetervak.tipcalculator.model.TipData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: TipDataRepository
) : ViewModel() {

    val tipCalculator = TipCalculator()

    private val _liveUiState = MutableLiveData(CalculatorUiState())
    val liveUiState: LiveData<CalculatorUiState> = _liveUiState

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
        saveTipData(tipData)
    }

    private fun saveTipData(tipData: TipData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertTipData(tipData)
        }
    }

    init {
       Log.d("DependencyInjection", "CalculatorViewModel is created")
    }

}