package simple.program.accentureexam.data.cache.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.data.cache.room.AppDatabase
import simple.program.accentureexam.util.AndroidTestCoroutineRule


@RunWith(AndroidJUnit4::class)
@SmallTest
class FlightsDaoTest {

    @get:Rule
    var coroutinesTestRule = AndroidTestCoroutineRule()

    private lateinit var database : AppDatabase
    private lateinit var dao : FlightsDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).setTransactionExecutor(coroutinesTestRule.testDispatcher.asExecutor())
            .setQueryExecutor(coroutinesTestRule.testDispatcher.asExecutor())
            .allowMainThreadQueries().build()
        dao = database.flightDao()
    }

    @After
    fun cleanup(){
        database.close()
    }


    @Test
    fun saveFlightsdata() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val savedItems = FlightsDataEntity("test", "test", "test", "test")
        dao.saveFlights(listOf(savedItems))

        val allFlights = dao.getFlights().first()

        assertThat(allFlights).contains(savedItems)
    }

    @Test
    fun deleteFlightsData() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val savedItems = FlightsDataEntity("test", "test", "test", "test")
        dao.saveFlights(listOf(savedItems))

        dao.deleteAllFlights()

        val allFlights = dao.getFlights().first()
        assertThat(allFlights).doesNotContain(savedItems)
    }

    @Test
    fun saveItemAndCheckTotalItemCount() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val savedItems1 = FlightsDataEntity("test", "test", "test", "test")
        val savedItems2 = FlightsDataEntity("test2", "test2", "test2", "test")
        dao.saveFlights(listOf(savedItems1, savedItems2 ))

        val allFlights = dao.getFlights().first()

        assertThat(allFlights).hasSize(2)
    }
}