package com.nab.forecast.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nab.forecast.framework.room.model.LocalWeatherInfo

@Database(entities = [LocalWeatherInfo::class], version = 1,exportSchema = false)
abstract class WeatherForecastDatabase : RoomDatabase() {
    abstract fun weatherForecastDao(): WeatherForecastDAO

    companion object {
        private const val DATABASE_NAME = "WeatherForecast"
        fun buildDatabase(context: Context): WeatherForecastDatabase {
            return Room.databaseBuilder(context, WeatherForecastDatabase::class.java, DATABASE_NAME).build()
        }
    }
}