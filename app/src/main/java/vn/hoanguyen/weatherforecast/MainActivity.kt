package vn.hoanguyen.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vn.hoanguyen.weatherforecast.presentation.ui.navigation.NavGraph
import vn.hoanguyen.weatherforecast.presentation.ui.theme.WeatherForecastApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastApplicationTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}