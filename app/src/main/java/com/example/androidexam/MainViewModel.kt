package com.example.androidexam

import androidx.lifecycle.*
import com.example.androidexam.data.flightData.FlightDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FlightDataRepository,
) : ViewModel() {

    private val _effect: Channel<Event> = Channel()
    val effect = _effect.receiveAsFlow()

    val flightData = repository.getFlightsData(
            onFetchSuccess = {  },
            onFetchFailed = { t ->
                viewModelScope.launch(Dispatchers.IO) { _effect.send(Event.ShowErrorMessage(t)) }
            }
        )

    sealed class Event {
        data class ShowErrorMessage(val error: Throwable?) : Event()
    }
}

