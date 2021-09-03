package simple.program.accentureexam.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import simple.program.accentureexam.data.remote.model.GetFlightsResponse

interface QantasService {

    @GET("flight/refData/airport")
    suspend fun getFlights() : GetFlightsResponse
}