package vn.hoanguyen.weatherforecast.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.hoanguyen.weatherforecast.data.db.RealmDatabase
import vn.hoanguyen.weatherforecast.data.repositories.FavoriteCityRepositoryImpl
import vn.hoanguyen.weatherforecast.data.repositories.RepositoryImpl
import vn.hoanguyen.weatherforecast.data.services.ApiService
import vn.hoanguyen.weatherforecast.domain.repositories.FavoriteCityRepository
import vn.hoanguyen.weatherforecast.domain.repositories.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideFavoriteCityRepository(
        realmDatabase: RealmDatabase
    ): FavoriteCityRepository = FavoriteCityRepositoryImpl(realmDatabase)
}
