package vn.hoanguyen.weatherforecast.data.providers

import retrofit2.Retrofit
import vn.hoanguyen.weatherforecast.data.services.ApiService

object ApiServiceProvider {

    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}