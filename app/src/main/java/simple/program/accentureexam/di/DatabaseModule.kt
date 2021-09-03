package simple.program.accentureexam.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import simple.program.accentureexam.data.cache.room.AppDatabase
import simple.program.accentureexam.data.cache.room.dao.FlightsDao
import simple.program.accentureexam.util.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constant.DATABASE_NAME
        ).build()
    }
}
