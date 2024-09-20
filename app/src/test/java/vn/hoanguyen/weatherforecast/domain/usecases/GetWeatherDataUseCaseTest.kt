package vn.hoanguyen.weatherforecast.domain.usecases

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import vn.hoanguyen.weatherforecast.domain.models.MainModel
import vn.hoanguyen.weatherforecast.domain.models.MainWeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WeatherModel
import vn.hoanguyen.weatherforecast.domain.models.WindModel
import vn.hoanguyen.weatherforecast.domain.repositories.Repository

class GetWeatherDataUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        getWeatherDataUseCase = GetWeatherDataUseCase(repository)
    }

    @Test
    fun `invoke should return weather data for a valid city name`() = runTest {
        // Arrange
        val cityName = "New York"
        val expectedWeatherData = MainWeatherModel(
            name = "London", main = MainModel(temp = 273.0, humidity = 4),
            wind = WindModel(speed = 4.3),
            weather = listOf(WeatherModel(description = "wind", icon = "04d"))
        )
        coEvery { repository.getWeather(cityName) } returns flowOf(expectedWeatherData)

        // Act
        val result = getWeatherDataUseCase(cityName).first()

        // Assert
        assertEquals(expectedWeatherData, result)
        coVerify(exactly = 1) { repository.getWeather(cityName) }
    }

    @Test
    fun `invoke should return empty flow when city is not found`() = runTest {
        // Arrange
        val cityName = "Unknown City"
        coEvery { repository.getWeather(cityName) } returns emptyFlow()

        // Act
        val result = getWeatherDataUseCase(cityName).toList()

        // Assert
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { repository.getWeather(cityName) }
    }
}