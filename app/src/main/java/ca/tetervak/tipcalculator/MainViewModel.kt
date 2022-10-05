package ca.tetervak.tipcalculator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.tipcalculator.data.TipDataRepository
import ca.tetervak.tipcalculator.model.TipData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TipDataRepository
) : ViewModel() {

    fun saveTipData(tipData: TipData) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTipData(tipData)
        }

    fun deleteTipDataById(id: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTipDataById(id)
        }

    fun clearAllHistory() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTipData()
        }

    init {
        Log.d("DependencyInjection", "MainViewModel is created")
    }
}