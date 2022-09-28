package ca.tetervak.tipcalculator.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [TipDataEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class TipDatabase : RoomDatabase() {

    abstract fun tipDataDao(): TipDataDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TipDatabase? = null

        fun getDatabase(context: Context): TipDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TipDatabase::class.java,
                    "tip_database"
                ).build()
                INSTANCE = instance
                Log.d("DependencyInjection","TipDatabase is created")
                // return instance
                instance
            }
        }
    }

}