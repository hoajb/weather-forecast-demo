package vn.hoanguyen.weatherforecast.data.services

import retrofit2.http.GET
import vn.hoanguyen.weatherforecast.data.response.MainWeatherResponse

interface ApiService {

    @GET("weather")
    suspend fun getWeatherResponse(): MainWeatherResponse

}