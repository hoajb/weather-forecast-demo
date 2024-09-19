package vn.hoanguyen.weatherforecast.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import vn.hoanguyen.weatherforecast.data.db.RealmDatabase
import vn.hoanguyen.weatherforecast.data.db.entities.RealmFavoriteCity
import vn.hoanguyen.weatherforecast.data.db.entities.toModel
import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel
import vn.hoanguyen.weatherforecast.domain.repositories.FavoriteCityRepository

class FavoriteCityRepositoryImpl(
    private val realm: RealmDatabase
) : FavoriteCityRepository {
    override fun saveFavoriteCity(name: String) =
        realm.addRealmFavoriteCity(RealmFavoriteCity().apply { this.name = name }).toModel()


    override fun getFavoriteCities(): Flow<List<FavoriteCityModel>> =
        realm.getAllRealmFavoriteCitiesAsFlow().map { it.map { entity -> entity.toModel() } }

    override fun deleteFavoriteCity(id: String) {
        realm.deleteRealmFavoriteCityById(id)
    }

    override fun getFavoriteCityById(id: String): Flow<FavoriteCityModel?> = flow {
        emit(realm.getRealmFavoriteCitiesById(id)?.toModel())
    }

    override fun getFavoriteCityByName(name: String): Flow<FavoriteCityModel?> = flow {
        emit(realm.getRealmFavoriteCitiesByName(name)?.toModel())
    }
}