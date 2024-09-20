package vn.hoanguyen.weatherforecast.presentation.ui.details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber
import vn.hoanguyen.weatherforecast.R
import vn.hoanguyen.weatherforecast.app.extensions.collectAsEffect
import vn.hoanguyen.weatherforecast.presentation.base.showToast
import vn.hoanguyen.weatherforecast.presentation.ui.common.TopAppBar
import vn.hoanguyen.weatherforecast.presentation.ui.favorite.FavoriteCitiesViewModel
import vn.hoanguyen.weatherforecast.presentation.ui.models.FavoriteCityUI
import vn.hoanguyen.weatherforecast.presentation.ui.theme.AppTypography

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel<DetailsViewModel>(),
    favoriteCitiesViewModel: FavoriteCitiesViewModel = hiltViewModel<FavoriteCitiesViewModel>(),
    onBack: () -> Unit,
    city: String,
) {
    val context = LocalContext.current
    viewModel.error.collectAsEffect { e -> e.showToast(context) }

    val favoriteCityUI: FavoriteCityUI? by favoriteCitiesViewModel.favoriteCity.collectAsStateWithLifecycle()
    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()
    val uiModel: WeatherDetailsUI by viewModel.uiModel.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getWeatherData(cityName = city)
        favoriteCitiesViewModel.loadFavoriteCity(cityName = city)
    }

    DetailsContent(
        onBack = onBack,
        city = city,
        isLoading = isLoading,
        uiModel = uiModel,
        isFavorite = favoriteCityUI != null,
        onFavoritePressed = {
            if (favoriteCityUI == null) {
                favoriteCitiesViewModel.addFavoriteCity(name = uiModel.name)
            } else {
                favoriteCitiesViewModel.removeFavoriteCity(cityId = favoriteCityUI?.id.orEmpty())
            }
        }
    )
}

@Composable
fun DetailsContent(
    onBack: () -> Unit,
    city: String,
    isLoading: Boolean,
    uiModel: WeatherDetailsUI,
    isFavorite: Boolean,
    onFavoritePressed: () -> Unit
) {

    Timber.d("icon: ${uiModel.icon}")
    Scaffold(topBar = {
        TopAppBar(title = city, onBack = onBack)
    }) { paddingValues ->
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (uiModel.notFound) {
                    Text(
                        stringResource(R.string.not_found),
                        modifier = Modifier.align(Alignment.Center),
                        style = AppTypography.bodyMedium.copy(color = Color.LightGray)
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "${context.getText(R.string.city)}: ${uiModel.name}",
                            style = AppTypography.titleMedium
                        )
                        Spacer(Modifier.height(30.dp))
                        Image(
                            modifier = Modifier
                                .width(72.dp)
                                .height(72.dp),
                            painter = painterResource(uiModel.icon),
                            contentDescription = "icon description"
                        )
                        Text(uiModel.temp, style = AppTypography.headlineLarge)
                        Text(uiModel.description, style = AppTypography.bodyMedium)
                        Spacer(Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            WeatherItem(
                                icon = R.drawable.humidity_percentage,
                                title = R.string.humidity,
                                value = uiModel.humidity
                            )
                            WeatherItem(
                                icon = R.drawable.ic_wind_power,
                                title = R.string.wind_speed,
                                value = uiModel.windSpeed
                            )
                        }
                    }



                    IconButton(
                        onClick = onFavoritePressed,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            if (isFavorite) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                            contentDescription = "favorite",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherItem(
    @DrawableRes icon: Int,
    @StringRes title: Int,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp),
            painter = painterResource(icon),
            contentDescription = "weather",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(value, style = AppTypography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(title))
        }
    }
}
