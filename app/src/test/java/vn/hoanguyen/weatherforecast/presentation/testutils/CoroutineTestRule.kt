package vn.hoanguyen.weatherforecast.presentation.testutils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import vn.hoanguyen.weatherforecast.app.util.DispatchersProvider

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : BeforeEachCallback, AfterEachCallback {

    val testDispatcherProvider = object : DispatchersProvider {
        override val io: CoroutineDispatcher = testDispatcher
        override val main: CoroutineDispatcher = testDispatcher
        override val default: CoroutineDispatcher = testDispatcher
    }

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)  // Set the Main dispatcher before each test
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()  // Reset the Main dispatcher after each test
    }
}
