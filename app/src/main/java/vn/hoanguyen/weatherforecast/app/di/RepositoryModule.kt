package vn.hoanguyen.weatherforecast.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.hoanguyen.weatherforecast.data.repositories.RepositoryImpl
import vn.hoanguyen.weatherforecast.data.services.ApiService
import vn.hoanguyen.weatherforecast.domain.repositories.Repository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}
