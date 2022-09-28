package ca.tetervak.tipcalculator.data

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideTipDataDao(database: TipDatabase): TipDataDao {
        Log.d("DependencyInjection", "provide TipDataDao")
        return database.tipDataDao()
    }

    @Singleton
    @Provides
    fun provideTipDatabase(@ApplicationContext context: Context): TipDatabase {
        Log.d("DependencyInjection","provide TipDatabase")
        return TipDatabase.getDatabase(context)
    }
}