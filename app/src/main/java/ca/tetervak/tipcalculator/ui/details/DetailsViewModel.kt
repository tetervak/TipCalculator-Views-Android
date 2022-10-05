package ca.tetervak.tipcalculator.ui.details

import android.util.Log
import androidx.lifecycle.*
import ca.tetervak.tipcalculator.data.TipDataRepository
import ca.tetervak.tipcalculator.model.TipData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TipDataRepository
): ViewModel() {

    private val itemId: Int = savedStateHandle["itemId"]!!

    val liveTipData: LiveData<TipData> = liveData {
        emit(repository.getTipDataById(itemId))
    }

    init {
        Log.d("DependencyInjection", "DetailsViewModel is created")
    }
}