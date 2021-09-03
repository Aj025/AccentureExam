package simple.program.accentureexam.data.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.data.cache.room.dao.FlightsDao

@Database(entities = [FlightsDataEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun flightDao(): FlightsDao
}

