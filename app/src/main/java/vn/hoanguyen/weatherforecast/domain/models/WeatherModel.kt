package vn.hoanguyen.weatherforecast.domain.models

data class MainWeatherModel(
    val main: MainModel, val name: String, val wind: WindModel, val weather: List<WeatherModel>
)

data class MainModel(
    val temp: Double, val humidity: Int
)

data class WeatherModel(
    val description: String,
    val icon: String
)

data class WindModel(
    val speed: Double
)