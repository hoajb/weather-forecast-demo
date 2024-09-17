package vn.hoanguyen.weatherforecast.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import vn.hoanguyen.weatherforecast.R
import vn.hoanguyen.weatherforecast.presentation.ui.common.TopAppBar

@Composable
fun FavoriteScreen(
    onBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(R.string.home_title_bar), onBack = onBack)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Favorite")
        }
    }
}