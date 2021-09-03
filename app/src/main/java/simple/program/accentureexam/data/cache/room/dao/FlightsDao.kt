package simple.program.accentureexam.data.cache.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import simple.program.accentureexam.data.cache.model.FlightsDataEntity

@Dao
interface FlightsDao {
    @Query("SELECT * FROM flights_data")
    fun getFlights(): Flow<List<FlightsDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlights(dtr : List<FlightsDataEntity>)


    @Query("DELETE FROM flights_data")
    suspend fun deleteAllFlights()
}