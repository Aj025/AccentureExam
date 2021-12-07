package com.example.androidexam.data.flightData

import androidx.room.withTransaction
import com.example.androidexam.data.flightData.FlightDataRepository
import com.example.androidexam.data.model.FlightsDataEntity
import com.example.androidexam.data.room.AppDatabase
import com.example.androidexam.util.Resource
import com.example.androidexam.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import simple.program.accentureexam.data.remote.services.QantasService
import java.io.IOException
import javax.inject.Inject

class FlightDataRepositoryImpl @Inject constructor(
    private val flightsApi: QantasService,
    private val appDb: AppDatabase
) : FlightDataRepository {

    private val flightsDao = appDb.flightDao()

    override fun getFlightsData(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (
            Throwable
        ) -> Unit
    ): Flow<Resource<List<FlightsDataEntity>>> =
        networkBoundResource(
            query = {
                flightsDao.getFlights()
            },
            fetch = {
                flightsApi.getFlights()
            },
            saveFetchResult = { flights ->
                appDb.withTransaction {
                    flightsDao.deleteAllFlights()
                    flightsDao.saveFlights(flights?.toDataModel())
                }
            },
            onFetchSuccess = onFetchSuccess,
            onFetchFailed = { t ->
                if (t !is HttpException && t !is IOException) {
                    throw t
                }
                onFetchFailed(t)
            }
        ).flowOn(Dispatchers.IO)
}