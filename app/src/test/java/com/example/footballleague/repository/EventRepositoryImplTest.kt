package com.example.footballleague.repository

import com.example.footballleague.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventRepositoryImplTest {

    @Mock
    private lateinit var eventRepository: EventRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.getEvents("4328")
                verify(eventRepository.getEvents("4328")) }
        }
    }

    @Test
    fun getPrevEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.getPrevEvents("4328")
                verify(eventRepository.getPrevEvents("4328"))
            }
        }
    }

    @Test
    fun getNextEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.getNextEvents("4328")
                verify(eventRepository.getNextEvents("4328"))
            }
        }
    }

    @Test
    fun saveFavoriteEvent() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        val event = Event("602268",
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
            null)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.saveFavoriteEvent(event)
                verify(eventRepository.saveFavoriteEvent(event))
            }
        }
    }

    @Test
    fun deleteFavoriteEvent() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.deleteFavoriteEvent("4328")
                verify(eventRepository.deleteFavoriteEvent("4328"))
            }
        }
    }

    @Test
    fun getPrevFavoriteEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.getPrevFavoriteEvents()
                verify(eventRepository.getPrevFavoriteEvents())
            }
        }
    }

    @Test
    fun getNextFavoriteEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)
        GlobalScope.launch{
            withContext(Dispatchers.IO) {
                eventRepository.getNextFavoriteEvents()
                verify(eventRepository.getNextFavoriteEvents())
            }
        }
    }
}