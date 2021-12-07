package simple.program.accentureexam.data.remote.services

import retrofit2.http.GET
import com.example.androidexam.data.remote.model.GetFlightsResponse

interface QantasService {

    @GET("flight/refData/airport")
    suspend fun getFlights() : GetFlightsResponse
}