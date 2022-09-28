package ca.tetervak.tipcalculator.data

import android.util.Log
import ca.tetervak.tipcalculator.model.TipData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TipDataRepositoryRoom @Inject constructor(
    private val tipDataDao: TipDataDao
) : TipDataRepository {

    override fun getTipDataListFlow(): Flow<List<TipData>> {
        return tipDataDao.getAllTipDataEntitiesFlow().map { entityList ->
            entityList.map { entity -> entity.toTipData() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertTipData(tipData: TipData) {
        tipDataDao.insetTipDataEntity(tipData.toTipDataEntity())
    }

    override suspend fun deleteTipDataById(id: Int) {
        tipDataDao.getTipDataEntityById(id)
    }

    override suspend fun getTipDataById(id: Int) =
        tipDataDao.getTipDataEntityById(id).toTipData()

    override suspend fun deleteAllTipData() {
        tipDataDao.deleteAllTipDataEntities()
    }

    init {
        Log.d("DependencyInjection", "TipDataRepositoryRoom is created")
    }
}

fun TipData.toTipDataEntity() = TipDataEntity(
    id, costOfService, serviceQuality, roundUpTip, tipAmount, billTotal, date
)

fun TipDataEntity.toTipData() = TipData(
    id, costOfService, serviceQuality, roundUpTip, tipAmount, billTotal, date
)