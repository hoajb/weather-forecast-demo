package vn.hoanguyen.weatherforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import vn.hoanguyen.weatherforecast.data.repositories.FavoriteCityRepositoryImpl

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}