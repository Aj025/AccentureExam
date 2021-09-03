package simple.program.accentureexam.presentation.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import simple.program.accentureexam.data.repository.flightData.FlightDataRepository
import simple.program.accentureexam.data.repository.flightData.FlightDataRepositoryImpl
import simple.program.accentureexam.di.IoDispatcher
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FlightDataRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _effect: Channel<Event> = Channel()
    val effect = _effect.receiveAsFlow()

    val flightData = repository.getFlightsData(
            onFetchSuccess = {  },
            onFetchFailed = { t ->
                viewModelScope.launch(ioDispatcher) { _effect.send(Event.ShowErrorMessage(t)) }
            }
        )

    sealed class Event {
        data class ShowErrorMessage(val error: Throwable?) : Event()
    }
}

