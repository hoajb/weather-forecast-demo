package vn.hoanguyen.weatherforecast.presentation.ui.navigation

private object Path {
    const val HOME = "home"
    const val DETAILS = "details"
    const val FAVORITE = "favorite"
}

sealed class NavRoute(val path: String) {
    data object Home : NavRoute(Path.HOME)
    data object Favorite : NavRoute(Path.FAVORITE)

    data object Details : NavRoute(Path.DETAILS) {
        const val CITY = "city"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}


