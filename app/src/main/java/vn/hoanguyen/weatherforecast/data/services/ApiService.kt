package vn.hoanguyen.weatherforecast.data.services

import retrofit2.http.GET
import retrofit2.http.Query
import vn.hoanguyen.weatherforecast.data.response.MainWeatherResponse

interface ApiService {

    @GET("weather")
    suspend fun getWeatherResponse(
        @Query("q") cityName: String,
        @Query("appid") appId: String
    ): MainWeatherResponse

}