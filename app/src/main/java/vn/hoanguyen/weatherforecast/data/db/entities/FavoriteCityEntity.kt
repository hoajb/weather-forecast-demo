package vn.hoanguyen.weatherforecast.data.db.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel
import java.util.UUID

class RealmFavoriteCity : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String = ""
}

fun RealmFavoriteCity.toModel() = FavoriteCityModel(id = id, name = name)