package vn.hoanguyen.weatherforecast.presentation.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vn.hoanguyen.weatherforecast.presentation.ui.common.TopAppBar

@Composable
fun DetailsScreen(
    onBack: () -> Unit,
    city: String,
) {
    Scaffold(topBar = {
        TopAppBar(title = city, onBack = onBack)
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("City: $city")
        }
    }
}