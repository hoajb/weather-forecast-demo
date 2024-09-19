package vn.hoanguyen.weatherforecast.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.hoanguyen.weatherforecast.app.util.DispatchersProvider
import vn.hoanguyen.weatherforecast.domain.usecases.DeleteFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.GetFavoriteCitiesUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.GetFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.SaveFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.presentation.base.BaseViewModel
import vn.hoanguyen.weatherforecast.presentation.ui.models.FavoriteCityUI
import vn.hoanguyen.weatherforecast.presentation.ui.models.toFavoriteCityUI
import javax.inject.Inject

@HiltViewModel
class FavoriteCitiesViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val saveFavoriteCityUseCase: SaveFavoriteCityUseCase,
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
    private val deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase,
    private val getFavoriteCityUseCase: GetFavoriteCityUseCase,
) : BaseViewModel() {

    private val _favoriteCities = MutableStateFlow<List<FavoriteCityUI>>(emptyList())
    val favoriteCities = _favoriteCities.asStateFlow()

    private val _favoriteCity = MutableStateFlow<FavoriteCityUI?>(null)
    val favoriteCity = _favoriteCity.asStateFlow()

    fun loadFavoriteCities() {
        getFavoriteCitiesUseCase().onEach { cities ->
            val favoriteCityUIModels = cities.map { city ->
                FavoriteCityUI(
                    name = city.name, id = city.id
                )
            }
            _favoriteCities.emit(favoriteCityUIModels)
        }.flowOn(dispatchersProvider.io).catch { e ->
            _error.emit(e)
        }.launchIn(viewModelScope)
    }

    fun addFavoriteCity(name: String) {
        launch(dispatchersProvider.io) {
            try {
                _favoriteCity.emit(saveFavoriteCityUseCase(name).toFavoriteCityUI())
            } catch (e: Exception) {
                _error.emit(e)
            }
        }
    }

    fun removeFavoriteCity(cityId: String) {
        launch(dispatchersProvider.io) {
            try {
                deleteFavoriteCityUseCase(cityId)
                _favoriteCity.emit(null)
            } catch (e: Exception) {
                _error.emit(e)
            }
        }
    }

    fun loadFavoriteCity(cityName: String) {
        launch(dispatchersProvider.io) {
            getFavoriteCityUseCase(cityName).onEach { city ->
                _favoriteCity.emit(city?.toFavoriteCityUI())
            }.flowOn(dispatchersProvider.io).catch { e ->
                _error.emit(e)
            }.launchIn(viewModelScope)
        }
    }
}