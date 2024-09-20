package vn.hoanguyen.weatherforecast.data.response

import vn.hoanguyen.weatherforecast.domain.models.MainModel
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WindModel

data class MainWeatherResponse(
    val name: String,
    val main: MainResponse,
    val wind: WindResponse,
    val weather: List<WeatherResponse>
)

data class MainResponse(
    val temp: Double,
    val humidity: Int
)

data class WeatherResponse(
    val description: String,
    val icon: String
)

data class WindResponse(
    val speed: Double
)

fun MainWeatherResponse.toModel() = MainWeatherModel(
    name = this.name,
    main = this.main.toModel(),
    wind = this.wind.toModel(),
    weather = this.weather.toModels(),
)

fun MainResponse.toModel() = MainModel(
    temp = this.temp,
    humidity = this.humidity,
)

fun WeatherResponse.toModel() = WeatherModel(
    description = this.description,
    icon = this.icon,
)

fun WindResponse.toModel() = WindModel(
    speed = this.speed,
)

fun List<WeatherResponse>.toModels() = this.map { it.toModel() }

//** Sample  https://openweathermap.org/current#name
// {
//  "coord": {
//    "lon": 10.99,
//    "lat": 44.34
//  },
//  "weather": [
//    {
//      "id": 501,
//      "main": "Rain",
//      "description": "moderate rain",
//      "icon": "10d"
//    }
//  ],
//  "base": "stations",
//  "main": {
//    "temp": 298.48,
//    "feels_like": 298.74,
//    "temp_min": 297.56,
//    "temp_max": 300.05,
//    "pressure": 1015,
//    "humidity": 64,
//    "sea_level": 1015,
//    "grnd_level": 933
//  },
//  "visibility": 10000,
//  "wind": {
//    "speed": 0.62,
//    "deg": 349,
//    "gust": 1.18
//  },
//  "rain": {
//    "1h": 3.16
//  },
//  "clouds": {
//    "all": 100
//  },
//  "dt": 1661870592,
//  "sys": {
//    "type": 2,
//    "id": 2075663,
//    "country": "IT",
//    "sunrise": 1661834187,
//    "sunset": 1661882248
//  },
//  "timezone": 7200,
//  "id": 3163858,
//  "name": "Zocca",
//  "cod": 200
//}     */