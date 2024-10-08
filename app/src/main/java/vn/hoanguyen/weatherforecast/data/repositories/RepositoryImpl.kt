package vn.hoanguyen.weatherforecast.data.repositories

import kotlinx.coroutines.flow.Flow
import vn.hoanguyen.weatherforecast.BuildConfig
import vn.hoanguyen.weatherforecast.data.extensions.flowTransform
import vn.hoanguyen.weatherforecast.data.response.toModel
import vn.hoanguyen.weatherforecast.data.services.ApiService
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.repositories.Repository

class RepositoryImpl(
    private val apiService: ApiService
) : Repository {

    override fun getWeather(cityName: String): Flow<MainWeatherModel> = flowTransform {
        apiService.getWeatherResponse(cityName, BuildConfig.WEATHER_API_KEY).toModel()
    }
}