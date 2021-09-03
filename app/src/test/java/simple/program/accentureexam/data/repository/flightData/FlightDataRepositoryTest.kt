package simple.program.accentureexam.data.repository.flightData

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.data.remote.model.GetFlightsResponse
import simple.program.accentureexam.util.Resource
import simple.program.accentureexam.util.networkBoundResource
import java.io.IOException

class FakeFlightDataRepository  : FlightDataRepository{

    private val dataList = listOf(
        FlightsDataEntity("test", "test", "test", "test"),
        FlightsDataEntity("test2", "test2", "test", "test"),
        FlightsDataEntity("test3", "test3", "test", "test")
    )

    private var shouldReturnNetworkError = false

    fun shouldReturnNetworkError(value : Boolean) {
        shouldReturnNetworkError = value
    }

    override fun getFlightsData(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<Resource<List<FlightsDataEntity>>> {
        return networkBoundResource(
            query = {
                flow { emit(dataList) }
            },
            fetch = {
                GetFlightsResponse().add(GetFlightsResponse.GetFlightsResponseItem("test1"))
            },
            saveFetchResult = {
                if (shouldReturnNetworkError)
                    throw IOException()
                else
                    dataList
            },
            onFetchSuccess = onFetchSuccess,
            onFetchFailed = onFetchFailed
        )
    }
}