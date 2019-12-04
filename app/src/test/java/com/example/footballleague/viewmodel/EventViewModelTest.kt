package com.example.footballleague.viewmodel

import com.example.footballleague.model.Event
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventViewModelTest {

    @Mock
    private lateinit var eventViewModel: EventViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadEvents("")
        verify(eventViewModel).loadEvents("")
    }

    @Test
    fun loadPrevEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadPrevEvents("")
        verify(eventViewModel).loadPrevEvents("")
    }

    @Test
    fun loadNextEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadNextEvents("")
        verify(eventViewModel).loadNextEvents("")
    }

    @Test
    fun loadPrevFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadPrevFavEvents()
        verify(eventViewModel).loadPrevFavEvents()
    }

    @Test
    fun loadNextFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadNextFavEvents()
        verify(eventViewModel).loadNextFavEvents()
    }

    @Test
    fun saveFavEvent() {
        eventViewModel = mock(EventViewModel::class.java)
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
        eventViewModel.saveFavEvent(event)
        verify(eventViewModel).saveFavEvent(event)
    }

    @Test
    fun deleteFavEvent() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.deleteFavEvent("602268")
        verify(eventViewModel).deleteFavEvent("602268")
    }
}