package com.example.footballleague.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.footballleague.model.Event
import com.example.footballleague.model.Events
import com.example.footballleague.model.EventsJson
import com.example.footballleague.repository.EventRepository
import com.example.footballleague.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class EventViewModelTest {

    @Mock
    private lateinit var repository: EventRepository

    private lateinit var eventViewModel: EventViewModel

    @Mock
    private lateinit var observer: Observer<List<Event>>

    @Mock
    private lateinit var observerSave: Observer<Long>

    @Mock
    private lateinit var observerDelete: Observer<Int>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        eventViewModel = EventViewModel(repository)
    }

    @Test
    fun loadEvents() {
        eventViewModel = mock(EventViewModel::class.java)

        val expected = listOf<Result<Events>>(
            Result.Error(Throwable()),
            Result.Success(Events(listOf<Event>()))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.getEvents("")).thenReturn(expected[0])
                eventViewModel.events.observeForever { observer }
                eventViewModel.loadEvents("")

                verify(observer).onChanged(listOf<Event>())
            }
        }
    }

    @Test
    fun loadPrevEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        val expected = listOf<Result<EventsJson>>(
            Result.Error(Throwable()),
            Result.Success(EventsJson(listOf<Event>()))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.getPrevEvents("")).thenReturn(expected[0])
                eventViewModel.prevEvents.observeForever { observer }
                eventViewModel.loadPrevEvents("")

                verify(observer).onChanged(listOf<Event>())
            }
        }
    }

    @Test
    fun loadNextEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        val expected = listOf<Result<EventsJson>>(
            Result.Error(Throwable()),
            Result.Success(EventsJson(listOf<Event>()))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.getNextEvents("")).thenReturn(expected[0])
                eventViewModel.nextEvents.observeForever { observer }
                eventViewModel.loadNextEvents("")

                verify(observer).onChanged(listOf<Event>())
            }
        }
    }

    @Test
    fun loadPrevFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        val expected = listOf<Result<List<Event>>>(
            Result.Error(Throwable()),
            Result.Success(listOf<Event>())
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.getPrevFavoriteEvents()).thenReturn(expected[0])
                eventViewModel.prevFavEvents.observeForever { observer }
                eventViewModel.loadPrevFavEvents()

                verify(observer).onChanged(listOf<Event>())
            }
        }
    }

    @Test
    fun loadNextFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        val expected = listOf<Result<List<Event>>>(
            Result.Error(Throwable()),
            Result.Success(listOf<Event>())
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.getNextFavoriteEvents()).thenReturn(expected[0])
                eventViewModel.nextFavEvents.observeForever { observer }
                eventViewModel.loadNextFavEvents()

                verify(observer).onChanged(listOf<Event>())
            }
        }
    }

    @Test
    fun saveFavEvent() {
        eventViewModel = mock(EventViewModel::class.java)

        val expected = listOf<Result<Long>>(
            Result.Error(Throwable()),
            Result.Success(1)
        )

        val event = Event(
            "602268",
            "4328",
            "Burnley vs Crystal Palace",
            "English Premier League",
            "Soccer",
            "Soccer",
            "Crystal Palace",
            "2019-11-30",
            "15:00:00",
            0,
            1,
            null,
            null,
            null
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.saveFavoriteEvent(event)).thenReturn(expected[0])
                eventViewModel.idSave.observeForever { observerSave }
                eventViewModel.saveFavEvent(event)

                verify(observerSave).onChanged(1)
            }
        }
    }

    @Test
    fun deleteFavEvent() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.deleteFavEvent("602268")
        verify(eventViewModel).deleteFavEvent("602268")

        val actual = MutableLiveData<Int>()
        actual.value = 1

        `when`(eventViewModel.idDelete).thenReturn(actual)
        Assert.assertEquals(1, eventViewModel.idDelete.value)

        val expected = listOf<Result<Int>>(
            Result.Error(Throwable()),
            Result.Success(1)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(repository.deleteFavoriteEvent("602268")).thenReturn(expected[0])
                eventViewModel.idDelete.observeForever { observerDelete }
                eventViewModel.deleteFavEvent("602268")

                verify(observerDelete).onChanged(1)
            }
        }
    }
}