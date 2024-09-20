package vn.hoanguyen.weatherforecast.data.providers

import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

object ConverterFactoryProvider {

    fun getGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}