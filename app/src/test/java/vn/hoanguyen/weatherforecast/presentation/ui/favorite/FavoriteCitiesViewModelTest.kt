package vn.hoanguyen.weatherforecast.presentation.ui.favorite

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import vn.hoanguyen.weatherforecast.domain.usecases.DeleteFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.GetFavoriteCitiesUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.GetFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.domain.usecases.SaveFavoriteCityUseCase
import vn.hoanguyen.weatherforecast.presentation.testutils.CoroutineTestRule
import vn.hoanguyen.weatherforecast.presentation.testutils.ModelUFactory.createFavoriteCityModel

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineTestRule::class)
class FavoriteCitiesViewModelTest {

    private lateinit var viewModel: FavoriteCitiesViewModel
    private val saveFavoriteCityUseCase: SaveFavoriteCityUseCase = mockk()
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase = mockk()
    private val deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase = mockk()
    private val getFavoriteCityUseCase: GetFavoriteCityUseCase = mockk()
    private val dispatchersProvider = CoroutineTestRule().testDispatcherProvider

    @BeforeEach
    fun setUp() {
        viewModel = FavoriteCitiesViewModel(
            dispatchersProvider,
            saveFavoriteCityUseCase,
            getFavoriteCitiesUseCase,
            deleteFavoriteCityUseCase,
            getFavoriteCityUseCase
        )
    }

    @Test
    fun `loadFavoriteCities should load favorite cities correctly`() = runBlocking {
        // Arrange
        val favoriteCitiesList = listOf(
            createFavoriteCityModel(city = "London"),
            createFavoriteCityModel(city = "Hanoi")
        )
        every { getFavoriteCitiesUseCase() } returns flowOf(favoriteCitiesList)

        // Act
        viewModel.loadFavoriteCities()

        // Assert
        viewModel.favoriteCities.test {
            val items = awaitItem()
            assertEquals(2, items.size)
            assertEquals("London", items[0].name)
            assertEquals("Hanoi", items[1].name)
        }
    }

    @Test
    fun `addFavoriteCity should add a city successfully`() = runBlocking {
        // Arrange
        val cityName = "New York"
        val savedCity = createFavoriteCityModel(cityName)
        every { saveFavoriteCityUseCase(cityName) } returns savedCity

        // Act
        viewModel.addFavoriteCity(cityName)

        // Assert
        viewModel.favoriteCity.test {
            val item = awaitItem()
            assertEquals(cityName, item?.name)
        }
    }

    @Test
    fun `removeFavoriteCity should remove a city successfully`() = runBlocking {
        // Arrange
        val cityId = "1"

        // Act
        viewModel.removeFavoriteCity(cityId)

        // Assert
        verify { deleteFavoriteCityUseCase.invoke(cityId) }
        viewModel.favoriteCity.test {
            val item = awaitItem()
            assertEquals(null, item)
        }
    }

    @Test
    fun `loadFavoriteCity should load a specific city correctly`() = runBlocking {
        // Arrange
        val cityName = "London"
        val city = createFavoriteCityModel(cityName)
        every { getFavoriteCityUseCase(cityName) } returns flowOf(city)

        // Act
        viewModel.loadFavoriteCity(cityName)

        // Assert
        viewModel.favoriteCity.test {
            val item = awaitItem()
            assertEquals(cityName, item?.name)
        }
    }
}