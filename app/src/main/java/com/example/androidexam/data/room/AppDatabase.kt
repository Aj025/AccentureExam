package com.example.androidexam.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidexam.data.model.FlightsDataEntity
import simple.program.accentureexam.data.cache.room.dao.FlightsDao

@Database(entities = [FlightsDataEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun flightDao(): FlightsDao
}

