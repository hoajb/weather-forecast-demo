package vn.hoanguyen.weatherforecast.domain.usecases

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import vn.hoanguyen.weatherforecast.domain.models.FavoriteCityModel
import vn.hoanguyen.weatherforecast.domain.repositories.FavoriteCityRepository
import java.util.UUID

class FavoriteCityUseCasesTest {

    private lateinit var repository: FavoriteCityRepository
    private lateinit var saveFavoriteCityUseCase: SaveFavoriteCityUseCase
    private lateinit var getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase
    private lateinit var deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase
    private lateinit var getFavoriteCityUseCase: GetFavoriteCityUseCase

    @Before
    fun setUp() {
        repository = mockk()
        saveFavoriteCityUseCase = SaveFavoriteCityUseCase(repository)
        getFavoriteCitiesUseCase = GetFavoriteCitiesUseCase(repository)
        deleteFavoriteCityUseCase = DeleteFavoriteCityUseCase(repository)
        getFavoriteCityUseCase = GetFavoriteCityUseCase(repository)
    }

    @Test
    fun `saveFavoriteCityUseCase should call repository saveFavoriteCity`() {
        // Arrange
        val cityName = "New York"
        every { repository.saveFavoriteCity(cityName) } returns FavoriteCityModel(
            id = UUID.randomUUID().toString(), name = cityName
        )

        // Act
        saveFavoriteCityUseCase(cityName)

        // Assert
        verify(exactly = 1) { repository.saveFavoriteCity(cityName) }
    }

    @Test
    fun `getFavoriteCitiesUseCase should return flow of favorite cities`() = runTest {
        // Arrange
        val cityList = listOf(
            FavoriteCityModel(id = UUID.randomUUID().toString(), name = "New York"),
            FavoriteCityModel(id = UUID.randomUUID().toString(), name = "London")
        )
        every { repository.getFavoriteCities() } returns flowOf(cityList)

        // Act
        val result = getFavoriteCitiesUseCase().first()

        // Assert
        assertEquals(cityList, result)
        verify(exactly = 1) { repository.getFavoriteCities() }
    }

    @Test
    fun `deleteFavoriteCityUseCase should call repository deleteFavoriteCity`() {
        // Arrange
        val cityId = "12345"
        every { repository.deleteFavoriteCity(cityId) } just Runs

        // Act
        deleteFavoriteCityUseCase(cityId)

        // Assert
        verify(exactly = 1) { repository.deleteFavoriteCity(cityId) }
    }

    @Test
    fun `getFavoriteCityUseCase should return the correct city by name`() = runTest {
        // Arrange
        val cityName = "New York"
        val city = FavoriteCityModel(id = UUID.randomUUID().toString(), name = cityName)
        every { repository.getFavoriteCityByName(cityName) } returns flowOf(city)

        // Act
        val result = getFavoriteCityUseCase(cityName).first()

        // Assert
        assertEquals(city, result)
        verify(exactly = 1) { repository.getFavoriteCityByName(cityName) }
    }

    @Test
    fun `getFavoriteCityUseCase should return null if city not found`() = runTest {
        // Arrange
        val cityName = "Unknown City"
        every { repository.getFavoriteCityByName(cityName) } returns flowOf(null)

        // Act
        val result = getFavoriteCityUseCase(cityName).first()

        // Assert
        assertNull(result)
        verify(exactly = 1) { repository.getFavoriteCityByName(cityName) }
    }
}