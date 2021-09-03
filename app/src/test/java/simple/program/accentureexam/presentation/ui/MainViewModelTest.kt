package simple.program.accentureexam.presentation.ui

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.data.repository.flightData.FakeFlightDataRepository
import simple.program.accentureexam.util.Resource
import simple.program.accentureexam.util.TestCoroutineRule


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var coroutinesTestRule = TestCoroutineRule()

    private lateinit var repository: FakeFlightDataRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        repository = FakeFlightDataRepository()
        viewModel = MainViewModel(repository, coroutinesTestRule.testDispatcher)
    }


    @Test
    fun `getFlightData and return data`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val result = mutableListOf<Resource<List<FlightsDataEntity>>>()
        viewModel.flightData.take(2).collect {
            result.add(it)
        }

        assertThat(result.size).isEqualTo(2)
        assertThat(result.last()).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `getFlightData and verify if loading was called first `() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result = mutableListOf<Resource<List<FlightsDataEntity>>>()
            viewModel.flightData.take(2).collect {
                result.add(it)
            }

            assertThat(result.size).isEqualTo(2)
            assertThat(result.first()).isInstanceOf(Resource.Loading::class.java)
        }

    @Test
    fun `getFlightData and return error`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        repository.shouldReturnNetworkError(true)
        val result = mutableListOf<Resource<List<FlightsDataEntity>>>()
        viewModel.flightData.take(2).collect {
            result.add(it)
        }

        assertThat(result.size).isEqualTo(2)
        assertThat(result.last()).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `getFlightData and return error with event ShowErrorMessage`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            repository.shouldReturnNetworkError(true)
            val result1 = mutableListOf<Resource<List<FlightsDataEntity>>>()

            viewModel.flightData.take(2).collect {
                result1.add(it)
            }

            val result2 = viewModel.effect.first()

            assertThat(result1.size).isEqualTo(2)
            assertThat(result1.last()).isInstanceOf(Resource.Error::class.java)
            assertThat(result2).isInstanceOf(MainViewModel.Event.ShowErrorMessage::class.java)
        }
}