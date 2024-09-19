package vn.hoanguyen.weatherforecast.domain.repositories

import kotlinx.coroutines.flow.Flow
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel

interface Repository {

    fun getWeather(cityName: String): Flow<MainWeatherModel>
}