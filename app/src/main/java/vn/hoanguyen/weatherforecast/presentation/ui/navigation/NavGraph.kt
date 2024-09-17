package vn.hoanguyen.weatherforecast.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import vn.hoanguyen.weatherforecast.presentation.ui.details.DetailsScreen
import vn.hoanguyen.weatherforecast.presentation.ui.favorite.FavoriteScreen
import vn.hoanguyen.weatherforecast.presentation.ui.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.Home.path
    ) {
        addHomeScreen(navController)
        addDetailsScreen(navController)
        addFavoriteScreen(navController)
    }
}

private fun NavGraphBuilder.addHomeScreen(
    navController: NavHostController,
) {
    composable(
        route = NavRoute.Home.path,
    ) { _ ->
        HomeScreen(
            onNavigateToDetails = { city ->
                navController.navigate(NavRoute.Details.withArgs(city))
            }, onNavigateToFavorite = {
                navController.navigate(NavRoute.Favorite.path)
            })
    }
}

private fun NavGraphBuilder.addDetailsScreen(
    navController: NavHostController,
) {
    composable(
        route = NavRoute.Details.withArgsFormat(NavRoute.Details.city),
        arguments = listOf(
            navArgument(NavRoute.Details.city) { type = NavType.StringType },
        )
    ) { backStackEntry ->
        DetailsScreen(
            city = backStackEntry.arguments?.getString(NavRoute.Details.city)
                .orEmpty(),
            onBack = { navController.popBackStack() },
        )
    }
}

private fun NavGraphBuilder.addFavoriteScreen(
    navController: NavHostController,
) {
    composable(
        route = NavRoute.Favorite.path,
    ) { _ ->
        FavoriteScreen(
            onBack = { navController.popBackStack() },
            onNavigateToDetails = { city ->
                navController.navigate(NavRoute.Details.withArgs(city))
            })
    }
}