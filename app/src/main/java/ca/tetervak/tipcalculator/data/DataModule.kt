package ca.tetervak.tipcalculator.data

import android.content.Context
import dagger.Binds
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Provides
    fun provideTipDataDao(database: TipDatabase): TipDataDao {
        return database.tipDataDao()
    }

    @Singleton
    @Provides
    fun provideTipDatabase(@ApplicationContext context: Context): TipDatabase {
       return TipDatabase.getDatabase(context)
    }

    @Singleton
    @Binds
    abstract fun bindTipDataRepository(repository: TipDataRepositoryRoom): TipDataRepository

}