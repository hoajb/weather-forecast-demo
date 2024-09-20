package vn.hoanguyen.weatherforecast.presentation.ui.details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.hoanguyen.weatherforecast.app.util.DispatchersProvider
import vn.hoanguyen.weatherforecast.domain.usecases.CapitalizeWordInString
import vn.hoanguyen.weatherforecast.domain.usecases.GetWeatherDataUseCase
import vn.hoanguyen.weatherforecast.presentation.base.BaseViewModel
import vn.hoanguyen.weatherforecast.presentation.ui.models.toUIModel
import vn.hoanguyen.weatherforecast.presentation.utils.WeatherIconParser
import javax.inject.Inject
import kotlin.math.floor

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val capitalizeWordInString: CapitalizeWordInString,
    private val weatherIconParser: WeatherIconParser,
) : BaseViewModel() {
    private val _uiModel = MutableStateFlow(WeatherDetailsUI.init())
    val uiModel = _uiModel.asStateFlow()

    fun getWeatherData(cityName: String) {
        getWeatherDataUseCase(cityName)
            .injectLoading()
            .onEach { result ->
                val weatherUIModel = result.toUIModel()
                val description =
                    if (weatherUIModel.weather.isNotEmpty()) weatherUIModel.weather[0].description else "Unknown"
                val iconName =
                    if (weatherUIModel.weather.isNotEmpty()) weatherUIModel.weather[0].icon else ""

                val weatherDetailsUI = WeatherDetailsUI(
                    notFound = false,
                    name = weatherUIModel.name,
                    temp = "${floor(weatherUIModel.main.temp).toInt() - 273} °C",
                    humidity = "${weatherUIModel.main.humidity} %",
                    windSpeed = "${weatherUIModel.wind.speed} Km/h",
                    icon = weatherIconParser.invoke(iconName),
                    description = capitalizeWordInString.invoke(description)
                )
                _uiModel.emit(weatherDetailsUI)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                _error.emit(e)
                _uiModel.emit(WeatherDetailsUI.init().copy(notFound = true))
            }
            .launchIn(viewModelScope)
    }
}