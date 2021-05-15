package com.nab.forecast.framework.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nab.forecast.framework.room.model.LocalWeatherInfo

@Dao
interface WeatherForecastDAO {

    @Query("SELECT * FROM weatherForecast WHERE cityName LIKE :cityName")
    suspend fun getWeatherForecastByCityName(cityName : String) : List<LocalWeatherInfo>

    @Query("delete FROM weatherForecast")
    suspend fun clearAllCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheCityWeatherForecast(localWeatherInfo: List<LocalWeatherInfo>)
}