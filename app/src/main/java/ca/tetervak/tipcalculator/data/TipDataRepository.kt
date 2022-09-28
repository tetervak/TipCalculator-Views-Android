package ca.tetervak.tipcalculator.data

import ca.tetervak.tipcalculator.model.TipData
import kotlinx.coroutines.flow.Flow

interface TipDataRepository {

    fun getTipDataListFlow(): Flow<List<TipData>>

    suspend fun getTipDataById(id: Int): TipData

    suspend fun insertTipData(tipData: TipData)

    suspend fun deleteTipDataById(id: Int)

    suspend fun deleteAllTipData()
}