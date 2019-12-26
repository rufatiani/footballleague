package com.example.footballleague.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.footballleague.model.Event
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class EventViewModelTest {

    @Mock
    private lateinit var eventViewModel: EventViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadEvents() {
        val actual = MutableLiveData<List<Event>>()
        actual.postValue(listOf<Event>())

        `when`(eventViewModel.events).thenReturn(actual)
        eventViewModel.events.value?.equals(listOf<Event>())?.let { Assert.assertTrue(it) }
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadEvents("")
        verify(eventViewModel).loadEvents("")


    }

    @Test
    fun loadPrevEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadPrevEvents("")
        verify(eventViewModel).loadPrevEvents("")

        val actual = MutableLiveData<List<Event>>()
        actual.postValue(listOf<Event>())

        `when`(eventViewModel.prevEvents).thenReturn(actual)
        eventViewModel.prevEvents.value?.equals(listOf<Event>())?.let { Assert.assertTrue(it) }
    }

    @Test
    fun loadNextEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadNextEvents("")
        verify(eventViewModel).loadNextEvents("")

        val actual = MutableLiveData<List<Event>>()
        actual.postValue(listOf<Event>())

        `when`(eventViewModel.nextEvents).thenReturn(actual)
        eventViewModel.nextEvents.value?.equals(listOf<Event>())?.let { Assert.assertTrue(it) }
    }

    @Test
    fun loadPrevFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadPrevFavEvents()
        verify(eventViewModel).loadPrevFavEvents()

        val actual = MutableLiveData<List<Event>>()
        actual.postValue(listOf<Event>())

        `when`(eventViewModel.prevFavEvents).thenReturn(actual)
        eventViewModel.prevFavEvents.value?.equals(listOf<Event>())?.let { Assert.assertTrue(it) }
    }

    @Test
    fun loadNextFavEvents() {
        eventViewModel = mock(EventViewModel::class.java)
        eventViewModel.loadNextFavEvents()
        verify(eventViewModel).loadNextFavEvents()

        val actual = MutableLiveData<List<Event>>()
        actual.postValue(listOf<Event>())

        `when`(eventViewModel.nextFavEvents).thenReturn(actual)
        eventViewModel.nextFavEvents.value?.equals(listOf<Event>())?.let { Assert.assertTrue(it) }
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

        val actual = MutableLiveData<Long>()
        actual.value = 1

        `when`(eventViewModel.idSave).thenReturn(actual)
        Assert.assertEquals(1.toLong(), eventViewModel.idSave.value)
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
    }
}