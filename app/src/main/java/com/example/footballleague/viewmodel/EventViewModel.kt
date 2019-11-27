package com.example.footballleague.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.model.Event
import com.example.footballleague.repository.EventRepository
import com.example.footballleague.utils.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class EventViewModel(private val eventRepository: EventRepository) : ViewModel(),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val events = MutableLiveData<List<Event>>()
    val prevEvents = MutableLiveData<List<Event>>()
    val nextEvents = MutableLiveData<List<Event>>()
    val prevFavEvents = MutableLiveData<List<Event>>()
    val nextFavEvents = MutableLiveData<List<Event>>()
    val idSave = MutableLiveData<Long>()
    val idDelete = MutableLiveData<Int>()

    fun loadEvents(query: String) {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.getEvents(query) }
            loading.value = false
            when (result) {
                is Result.Success -> events.value = result.data?.list?.filter { it -> it.sport.equals("Soccer") }
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadPrevEvents(query: String) {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.getPrevEvents(query) }
            loading.value = false
            when (result) {
                is Result.Success -> prevEvents.value = result.data?.list
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadNextEvents(query: String) {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.getNextEvents(query) }
            loading.value = false
            when (result) {
                is Result.Success -> nextEvents.value = result.data?.list
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadPrevFavEvents() {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.getPrevFavoriteEvents() }
            loading.value = false
            when (result) {
                is Result.Success -> prevFavEvents.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadNextFavEvents() {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.getNextFavoriteEvents() }
            loading.value = false
            when (result) {
                is Result.Success -> prevFavEvents.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun saveFavEvent(event: Event) {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.saveFavoriteEvent(event) }
            loading.value = false
            when (result) {
                is Result.Success -> idSave.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun deleteFavEvent(idEvent: String) {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { eventRepository.deleteFavoriteEvent(idEvent) }
            loading.value = false
            when (result) {
                is Result.Success -> idDelete.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}