package vn.hoanguyen.weatherforecast.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.hoanguyen.weatherforecast.app.util.DispatchersProvider
import vn.hoanguyen.weatherforecast.app.util.DispatchersProviderImpl

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl()
    }
}