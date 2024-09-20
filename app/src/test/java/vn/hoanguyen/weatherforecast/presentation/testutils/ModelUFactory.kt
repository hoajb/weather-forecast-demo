package vn.hoanguyen.weatherforecast.presentation.testutils

import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel
import vn.hoanguyen.weatherforecast.domain.models.MainModel
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WindModel
import java.util.UUID

object ModelUFactory {
    fun createMainWeatherModel(
        city: String = "London",
        description: String = "windy",
        icon: String = "01d",
    ) = MainWeatherModel(
        name = city,
        wind = WindModel(speed = 1.0),
        main = MainModel(temp = 273.0, humidity = 1),
        weather = listOf(WeatherModel(description = description, icon = icon))
    )

    fun createFavoriteCityModel(city: String = "London") = FavoriteCityModel(
        name = city,
        id = UUID.randomUUID().toString()
    )

    fun createFavoriteCityModelList() =
        listOf(createFavoriteCityModel(city = "London"), createFavoriteCityModel(city = "Hanoi"))
}