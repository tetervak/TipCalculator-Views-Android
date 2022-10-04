package ca.tetervak.tipcalculator.ui.history

import android.util.Log
import androidx.lifecycle.*
import ca.tetervak.tipcalculator.data.TipDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: TipDataRepository
) : ViewModel() {

    val liveItemUiStateList: LiveData<List<HistoryItemUiState>> =
        repository.getTipDataListFlow().map { tipDataList ->
            tipDataList.map { tipData ->
                HistoryItemUiState(
                    id = tipData.id,
                    date = tipData.date,
                    tipAmount = tipData.tipAmount,
                    billTotal = tipData.billTotal
                )
            }
        }.flowOn(Dispatchers.IO).asLiveData()

    fun deleteTipDataById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTipDataById(id)
    }

    fun clearAllHistory() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllTipData()
    }

    init {
        Log.d("DependencyInjection", "HistoryViewModel is created")
    }

}