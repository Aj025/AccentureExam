package com.example.androidexam.data.flightData

import com.example.androidexam.data.model.FlightsDataEntity
import com.example.androidexam.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlightDataRepository {
    fun getFlightsData(
        onFetchSuccess: () -> Unit,
        onFetchFailed: (
            Throwable
        ) -> Unit
    ): Flow<Resource<List<FlightsDataEntity>>>
}