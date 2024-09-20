package vn.hoanguyen.weatherforecast.presentation.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import vn.hoanguyen.weatherforecast.domain.models.MainModel
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WindModel

@Parcelize
data class MainWeatherUIModel(
    val main: MainUIModel,
    val name: String,
    val wind: WindUIModel,
    val weather: List<WeatherUIModel>
) : Parcelable

@Parcelize
data class MainUIModel(
    val temp: Double, val humidity: Int
) : Parcelable

@Parcelize
data class WeatherUIModel(
    val description: String,
    val icon: String
) : Parcelable

@Parcelize
data class WindUIModel(
    val speed: Double
) : Parcelable

fun MainWeatherModel.toUIModel() = MainWeatherUIModel(
    main = this.main.toUIModel(),
    name = this.name,
    weather = this.weather.toUIModels(),
    wind = this.wind.toUIModel()
)

fun MainModel.toUIModel() = MainUIModel(
    temp = this.temp,
    humidity = this.humidity,
)

fun WeatherModel.toUIModel() = WeatherUIModel(
    description = this.description,
    icon = this.icon
)

fun WindModel.toUIModel() = WindUIModel(
    speed = this.speed,
)

fun List<WeatherModel>.toUIModels() = this.map { it.toUIModel() }