package ca.tetervak.tipcalculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.tetervak.tipcalculator.model.ServiceQuality
import java.util.*

@Entity(tableName = "tip_data")
data class TipDataEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "cost")
    val costOfService: Double,

    @ColumnInfo(name = "quality")
    val serviceQuality: ServiceQuality,

    @ColumnInfo(name = "rounded_tip")
    val roundUpTip: Boolean,

    @ColumnInfo(name = "tip_amount")
    val tipAmount: Double,

    @ColumnInfo(name = "bill_total")
    val billTotal: Double,

    val date: Date
)