package vn.hoanguyen.weatherforecast.domain.usecases

import kotlinx.coroutines.flow.Flow
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherDataUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(cityName: String): Flow<MainWeatherModel> {
        return repository.getWeather(cityName)
    }
}

