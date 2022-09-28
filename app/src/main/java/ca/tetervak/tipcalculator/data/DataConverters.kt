package ca.tetervak.tipcalculator.data

import androidx.room.TypeConverter
import ca.tetervak.tipcalculator.model.ServiceQuality
import java.util.*

class DataConverters {

    @TypeConverter
    fun convertFromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun convertFromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertFromQualityToString(quality: ServiceQuality): String {
        return quality.toString()
    }

    @TypeConverter
    fun convertFromStringToQuality(quality: String): ServiceQuality {
        return ServiceQuality.valueOf(quality)
    }

}