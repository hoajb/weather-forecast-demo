package vn.hoanguyen.weatherforecast.presentation.ui.details

import androidx.annotation.DrawableRes
import vn.hoanguyen.weatherforecast.R

data class WeatherDetailsUI(
    val notFound: Boolean,
    val name: String,
    val temp: String,
    val humidity: String,
    val windSpeed: String,
    val description: String,
    @DrawableRes val icon: Int,
) {
    companion object {
        fun init(): WeatherDetailsUI {
            return WeatherDetailsUI(
                notFound = false,
                name = "-",
                temp = "-",
                humidity = "-",
                windSpeed = "-",
                description = "-",
                icon = R.drawable.icon_01d
            )
        }
    }
}