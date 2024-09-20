package vn.hoanguyen.weatherforecast.domain.repositories

import kotlinx.coroutines.flow.Flow
import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel

interface FavoriteCityRepository {
    fun saveFavoriteCity(name: String): FavoriteCityModel
    fun getFavoriteCities(): Flow<List<FavoriteCityModel>>
    fun deleteFavoriteCity(id: String)
    fun getFavoriteCityById(id: String): Flow<FavoriteCityModel?>
    fun getFavoriteCityByName(name: String): Flow<FavoriteCityModel?>
}