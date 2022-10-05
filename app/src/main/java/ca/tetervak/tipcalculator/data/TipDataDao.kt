package ca.tetervak.tipcalculator.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDataDao {

    @Query("SELECT * FROM tip_data")
    fun getAllTipDataEntitiesFlow(): Flow<List<TipDataEntity>>

    @Query("SELECT * FROM tip_data WHERE id = :id")
    suspend fun getTipDataEntityById(id: Int): TipDataEntity

    @Insert
    suspend fun insetTipDataEntity(tipDataEntity: TipDataEntity)

    @Update
    suspend fun updateTipDataEntity(tipDataEntity: TipDataEntity)

    @Delete
    suspend fun deleteTipDataEntity(tipDataEntity: TipDataEntity)

    @Query("DELETE FROM tip_data WHERE id=:id")
    suspend fun deleteTipDataEntityById(id: Int)

    @Query("DELETE FROM tip_data")
    suspend fun deleteAllTipDataEntities()
}