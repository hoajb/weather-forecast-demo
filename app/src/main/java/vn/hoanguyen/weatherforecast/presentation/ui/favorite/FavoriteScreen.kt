package vn.hoanguyen.weatherforecast.presentation.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vn.hoanguyen.weatherforecast.R
import vn.hoanguyen.weatherforecast.app.extensions.collectAsEffect
import vn.hoanguyen.weatherforecast.presentation.base.showToast
import vn.hoanguyen.weatherforecast.presentation.ui.common.TopAppBar
import vn.hoanguyen.weatherforecast.presentation.ui.models.FavoriteCityUI
import vn.hoanguyen.weatherforecast.presentation.ui.theme.AppTypography

@Composable
fun FavoriteScreen(
    viewModel: FavoriteCitiesViewModel = hiltViewModel<FavoriteCitiesViewModel>(),
    onBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
) {
    val context = LocalContext.current
    viewModel.error.collectAsEffect { e -> e.showToast(context) }

    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()
    val favoriteCities by viewModel.favoriteCities.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadFavoriteCities()
    }

    FavoriteContent(
        cities = favoriteCities,
        isLoading = isLoading,
        onBack = onBack,
        onNavigateToDetails = onNavigateToDetails
    )
}

@Composable
fun FavoriteContent(
    cities: List<FavoriteCityUI>,
    isLoading: Boolean,
    onBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = stringResource(R.string.favorited_city), onBack = onBack)
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                FavoriteCityList(cities = cities, onCityClick = onNavigateToDetails)
            }
        }
    }
}

@Composable
fun FavoriteCityItem(city: FavoriteCityUI, onClick: (String) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(city.id) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = city.name, style = AppTypography.bodyMedium)
    }
}

@Composable
fun FavoriteCityList(cities: List<FavoriteCityUI>, onCityClick: (String) -> Unit) {
    if (cities.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                stringResource(R.string.not_found_favorite),
                modifier = Modifier.align(Alignment.Center),
                style = AppTypography.bodyMedium.copy(color = Color.LightGray)
            )
        }
    } else {
        LazyColumn {
            items(cities) { city ->
                FavoriteCityItem(city = city, onClick = {
                    onCityClick.invoke(city.name)
                })
            }
        }
    }
}