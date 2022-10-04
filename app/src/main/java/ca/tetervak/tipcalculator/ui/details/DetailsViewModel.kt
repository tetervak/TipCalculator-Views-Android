package ca.tetervak.tipcalculator.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ca.tetervak.tipcalculator.data.TipDataRepository
import ca.tetervak.tipcalculator.model.TipData
import dagger.hilt.android.lifecycle.HiltViewModel
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
}