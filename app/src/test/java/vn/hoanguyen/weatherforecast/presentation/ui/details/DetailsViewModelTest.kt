package vn.hoanguyen.weatherforecast.presentation.ui.details

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import vn.hoanguyen.weatherforecast.app.util.DispatchersProvider
import vn.hoanguyen.weatherforecast.domain.usecases.CapitalizeWordInString
import vn.hoanguyen.weatherforecast.domain.usecases.GetWeatherDataUseCase
import vn.hoanguyen.weatherforecast.presentation.testutils.CoroutineTestRule
import vn.hoanguyen.weatherforecast.presentation.testutils.ModelUFactory
import vn.hoanguyen.weatherforecast.presentation.utils.WeatherIconParser

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineTestRule::class)
class DetailsViewModelTest {

    private val getWeatherDataUseCase: GetWeatherDataUseCase = mockk()
    private val capitalizeWordInString: CapitalizeWordInString = mockk()
    private val weatherIconParser: WeatherIconParser = mockk()

    private lateinit var viewModel: DetailsViewModel

    private val coroutineRule = CoroutineTestRule()

    @BeforeEach
    fun setup() {
        initViewModel()
    }

    @Test
    fun `getWeatherData emits correct UI model when successful`() = runTest {
        initViewModel(dispatchers = coroutineRule.testDispatcherProvider)
        // Arrange
        val cityName = "London"
        val description = "clear sky"
        val icon = "01d"
        val weatherData = ModelUFactory.createMainWeatherModel(
            city = cityName, description = description, icon = icon
        )
        val flow = flowOf(weatherData) // Mocking Result.success with weatherData

        every { getWeatherDataUseCase(cityName) } returns flow
        every { capitalizeWordInString.invoke(description) } returns "Clear Sky"
        every { weatherIconParser.invoke(icon) } returns 1

        // Act
        viewModel.getWeatherData(cityName)

        // Assert
        val expectedUIModel = WeatherDetailsUI(
            notFound = false,
            name = "London",
            temp = "0 °C",
            humidity = "1 %",
            windSpeed = "1.0 Km/h",
            icon = 1,
            description = "Clear Sky"
        )

        viewModel.uiModel.test {
            assertEquals(expectedUIModel, awaitItem())  // Collect the first emitted item
            cancelAndConsumeRemainingEvents()  // Ensure no further emissions are processed
        }
    }

    @Test
    fun `getWeatherData emits notFound when an error occurs`() = runTest {
        initViewModel(dispatchers = coroutineRule.testDispatcherProvider)
        // Arrange
        val cityName = "Unknown"
        val error = Throwable("City not found")

        every { getWeatherDataUseCase(cityName) } returns flow { throw error }

        // Act
        viewModel.getWeatherData(cityName)

        // Assert
        viewModel.uiModel.test {
            val emittedModel = awaitItem()
            assertEquals(true, emittedModel.notFound)
            cancelAndConsumeRemainingEvents()
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutineRule.testDispatcherProvider) {
        viewModel = DetailsViewModel(
            dispatchersProvider = dispatchers,
            getWeatherDataUseCase = getWeatherDataUseCase,
            capitalizeWordInString = capitalizeWordInString,
            weatherIconParser = weatherIconParser
        )
    }
}