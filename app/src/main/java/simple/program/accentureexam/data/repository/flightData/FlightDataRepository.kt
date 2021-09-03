package simple.program.accentureexam.data.repository.flightData

import kotlinx.coroutines.flow.Flow
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.util.Resource

interface FlightDataRepository {
    fun getFlightsData(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (
            Throwable
        ) -> Unit
    ): Flow<Resource<List<FlightsDataEntity>>>
}