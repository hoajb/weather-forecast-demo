package vn.hoanguyen.weatherforecast.data.db

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vn.hoanguyen.weatherforecast.data.db.entities.RealmFavoriteCity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmDatabase @Inject constructor() {

    private val realm: Realm by lazy {
        val configuration = RealmConfiguration.Builder(setOf(RealmFavoriteCity::class))
            .name("weather_app.realm")
            .build()
        Realm.open(configuration)
    }

    fun getAllRealmFavoriteCity(): List<RealmFavoriteCity> {
        return realm.query<RealmFavoriteCity>().find()
    }

    fun getAllRealmFavoriteCitiesAsFlow(): Flow<List<RealmFavoriteCity>> {
        return realm.query<RealmFavoriteCity>().asFlow().map { it.list }
    }

    fun getRealmFavoriteCitiesByName(cityName: String): RealmFavoriteCity? {
        return realm.query<RealmFavoriteCity>("name = $0", cityName).first().find()
    }

    fun getRealmFavoriteCitiesById(id: String): RealmFavoriteCity? {
        return realm.query<RealmFavoriteCity>("id = $0", id).first().find()
    }

    fun addRealmFavoriteCity(favoriteCity: RealmFavoriteCity) = realm.writeBlocking {
        copyToRealm(favoriteCity)
    }

    fun deleteRealmFavoriteCity(cityName: String) {
        realm.writeBlocking {
            query<RealmFavoriteCity>("name = $0", cityName)
                .first()
                .find()
                ?.let { delete(it) }
                ?: throw IllegalStateException("City not found.")
        }
    }

    fun deleteRealmFavoriteCityById(id: String) {
        realm.writeBlocking {
            query<RealmFavoriteCity>("id = $0", id)
                .first()
                .find()
                ?.let { delete(it) }
                ?: throw IllegalStateException("City not found.")
        }
    }

    fun clearAllRealmFavoriteCities() {
        realm.writeBlocking {
            delete(query<RealmFavoriteCity>())
        }
    }
}