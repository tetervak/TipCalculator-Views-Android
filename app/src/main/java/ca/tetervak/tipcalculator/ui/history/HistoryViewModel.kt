package ca.tetervak.tipcalculator.ui.history

import android.util.Log
import androidx.lifecycle.ViewModel
import ca.tetervak.tipcalculator.data.TipDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: TipDataRepository
) : ViewModel() {

    init {
        Log.d("DependencyInjection", "HistoryViewModel is created")
    }
}