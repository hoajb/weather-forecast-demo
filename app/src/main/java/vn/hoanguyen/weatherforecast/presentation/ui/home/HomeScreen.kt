package vn.hoanguyen.weatherforecast.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vn.hoanguyen.weatherforecast.R
import vn.hoanguyen.weatherforecast.presentation.ui.common.TopAppBar

@Composable
fun HomeScreen(
    onNavigateToDetails: (String) -> Unit,
    onNavigateToFavorite: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(title = stringResource(R.string.home_title_bar))
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var cityNameValue by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = cityNameValue,
                    onValueChange = { newText ->
                        cityNameValue = newText
                    },
                    label = { Text("Enter City Name") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    enabled = cityNameValue.isNotEmpty(), onClick = {
                        onNavigateToDetails.invoke(cityNameValue)
                    }) {
                    Text("Show")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    onNavigateToFavorite.invoke()
                }) {
                    Text("Favorite")
                }
            }
        }
    }
}