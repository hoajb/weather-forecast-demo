package vn.hoanguyen.weatherforecast.presentation.ui.models

import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel

data class FavoriteCityUI(
    val id: String,
    val name: String
)

fun FavoriteCityModel.toFavoriteCityUI() = FavoriteCityUI(
    id = this.id,
    name = this.name,
)