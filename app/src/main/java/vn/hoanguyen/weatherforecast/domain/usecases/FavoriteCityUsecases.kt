package vn.hoanguyen.weatherforecast.domain.usecases

import kotlinx.coroutines.flow.Flow
import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel
import vn.hoanguyen.weatherforecast.domain.repositories.FavoriteCityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveFavoriteCityUseCase @Inject constructor(private val repository: FavoriteCityRepository) {
    operator fun invoke(name: String) = repository.saveFavoriteCity(name)
}

@Singleton
class GetFavoriteCitiesUseCase @Inject constructor(private val repository: FavoriteCityRepository) {
    operator fun invoke(): Flow<List<FavoriteCityModel>> {
        return repository.getFavoriteCities()
    }
}

@Singleton
class DeleteFavoriteCityUseCase @Inject constructor(private val repository: FavoriteCityRepository) {
    operator fun invoke(id: String) {
        repository.deleteFavoriteCity(id)
    }
}

@Singleton
class GetFavoriteCityUseCase @Inject constructor(private val repository: FavoriteCityRepository) {
    operator fun invoke(cityName: String): Flow<FavoriteCityModel?> {
        return repository.getFavoriteCityByName(cityName)
    }
}