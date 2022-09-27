package ca.tetervak.tipcalculator.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator.model.ServiceQuality
import ca.tetervak.tipcalculator.model.TipCalculator

class CalculatorViewModel : ViewModel() {

    val tipCalculator = TipCalculator()

    private val _liveTipAmount = MutableLiveData<Double>(0.0)
    val liveTipAmount: LiveData<Double> = _liveTipAmount

    private val _liveBillTotal = MutableLiveData<Double>(0.0)
    val liveBillTotal: LiveData<Double> = _liveBillTotal

    private val _liveShowOutputs = MutableLiveData<Boolean>(false)
    val liveShowOutputs: LiveData<Boolean> = _liveShowOutputs

    fun setShowOutputs(showOutputs: Boolean){
        _liveShowOutputs.value = showOutputs
    }

    fun calculate(
        costOfService: Double,
        serviceQuality: ServiceQuality,
        roundUpTip: Boolean
    ) {
        val tipData = tipCalculator.calculate(costOfService, serviceQuality, roundUpTip)
        _liveTipAmount.value = tipData.tipAmount
        _liveBillTotal.value = tipData.billTotal
    }

}