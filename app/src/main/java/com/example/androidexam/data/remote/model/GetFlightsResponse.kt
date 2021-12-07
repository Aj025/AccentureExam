package com.example.androidexam.data.remote.model

import com.example.androidexam.data.model.FlightsDataEntity


class GetFlightsResponse : ArrayList<GetFlightsResponse.GetFlightsResponseItem>() {
    data class GetFlightsResponseItem(
        val airportCode: String = "",
        val airportName: String = "",
        val city: City = City(),
        val country: Country = Country(),
        val domesticAirport: Boolean = false,
        val eticketableAirport: Boolean = false,
        val internationalAirport: Boolean = false,
        val location: Location = Location(),
        val onlineIndicator: Boolean = false,
        val preferredInternationalAirportCode: String = "",
        val region: Region = Region(),
        val regionalAirport: Boolean = false
    ) {
        data class City(
            val cityCode: String = "",
            val cityName: String = "",
            val timeZoneName: String = ""
        )

        data class Country(
            val countryCode: String = "",
            val countryName: String = ""
        )

        data class Location(
            val aboveSeaLevel: Int = 0,
            val latitude: Double = 0.0,
            val latitudeDirection: String = "",
            val latitudeRadius: Double = 0.0,
            val longitude: Double = 0.0,
            val longitudeDirection: String = "",
            val longitudeRadius: Double = 0.0
        )

        data class Region(
            val regionCode: String = "",
            val regionName: String = ""
        )
    }


    fun toDataModel(): List<FlightsDataEntity> {
        val list = mutableListOf<FlightsDataEntity>()
        for (a in this) {
            list.add(
                FlightsDataEntity(
                    airportCode = a.airportCode,
                    airportName = a.airportName,
                    cityCode = a.city.cityName,
                    cityName = a.city.cityName,
                    timeZoneName = a.city.timeZoneName,
                    countryCode = a.country.countryCode,
                    countryName = a.country.countryName,
                )
            )
        }
        return list
    }
}