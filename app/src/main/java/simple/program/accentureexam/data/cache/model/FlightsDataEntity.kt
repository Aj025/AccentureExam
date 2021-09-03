package simple.program.accentureexam.data.cache.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import simple.program.accentureexam.data.remote.model.GetFlightsResponse



@Entity(tableName =  "flights_data")
@Parcelize
data class FlightsDataEntity(
    @PrimaryKey(autoGenerate = false)
    val airportCode: String = "",
    val airportName: String = "",
    val cityCode: String = "",
    val cityName: String = "",
    val timeZoneName: String = "",
    val countryCode: String = "",
    val countryName: String = ""
) : Parcelable
