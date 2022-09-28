package ca.tetervak.tipcalculator.ui.history

import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator.data.TipDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: TipDataRepository
) : ViewModel() {


}