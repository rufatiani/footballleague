package com.example.footballleague.repository

import com.example.footballleague.model.Event
import com.example.footballleague.model.Events
import com.example.footballleague.model.EventsJson
import com.example.footballleague.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
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

        val list = ArrayList<Event>()
        list.add(event)

        val expected = listOf<Result<Events>>(
            Result.Error(Throwable()),
            Result.Success(Events(list))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.getEvents("4328")).thenReturn(expected[0])
                eventRepository.getEvents("4328")
                verify(eventRepository.getEvents("4328"))
                Assert.assertEquals(eventRepository.getEvents("4328"), expected[0])
            }
        }
    }

    @Test
    fun getPrevEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)

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

        val list = ArrayList<Event>()
        list.add(event)

        val expected = listOf<Result<EventsJson>>(
            Result.Error(Throwable()),
            Result.Success(EventsJson(list))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.getPrevEvents("4328")).thenReturn(expected[0])
                eventRepository.getPrevEvents("4328")
                verify(eventRepository.getPrevEvents("4328"))
                Assert.assertEquals(eventRepository.getPrevEvents("4328"), expected[0])
            }
        }
    }

    @Test
    fun getNextEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)

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

        val list = ArrayList<Event>()
        list.add(event)

        val expected = listOf<Result<EventsJson>>(
            Result.Error(Throwable()),
            Result.Success(EventsJson(list))
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.getNextEvents("4328")).thenReturn(expected[0])
                eventRepository.getNextEvents("4328")
                verify(eventRepository.getNextEvents("4328"))
                Assert.assertEquals(eventRepository.getNextEvents("4328"), expected[0])
            }
        }
    }

    @Test
    fun saveFavoriteEvent() {
        eventRepository = Mockito.mock(eventRepository::class.java)
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

        val expected = listOf<Result<Long>>(
            Result.Error(Throwable()),
            Result.Success(1)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.saveFavoriteEvent(event)).thenReturn(expected[0])
                eventRepository.saveFavoriteEvent(event)
                verify(eventRepository.saveFavoriteEvent(event))
                Assert.assertEquals(eventRepository.saveFavoriteEvent(event), expected[0])
            }
        }
    }

    @Test
    fun deleteFavoriteEvent() {
        eventRepository = Mockito.mock(eventRepository::class.java)

        val expected = listOf<Result<Int>>(
            Result.Error(Throwable()),
            Result.Success(1)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.deleteFavoriteEvent("4328")).thenReturn(expected[0])
                eventRepository.deleteFavoriteEvent("4328")
                verify(eventRepository.deleteFavoriteEvent("4328"))
                Assert.assertEquals(eventRepository.deleteFavoriteEvent("4328"), expected[0])
            }
        }
    }

    @Test
    fun getPrevFavoriteEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)

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

        val list = ArrayList<Event>()
        list.add(event)

        val expected = listOf<Result<List<Event>>>(
            Result.Error(Throwable()),
            Result.Success(list)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.getPrevFavoriteEvents()).thenReturn(expected[0])
                eventRepository.getPrevFavoriteEvents()
                verify(eventRepository.getPrevFavoriteEvents())
                Assert.assertEquals(eventRepository.getPrevFavoriteEvents(), expected[0])
            }
        }
    }

    @Test
    fun getNextFavoriteEvents() {
        eventRepository = Mockito.mock(eventRepository::class.java)

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

        val list = ArrayList<Event>()
        list.add(event)

        val expected = listOf<Result<List<Event>>>(
            Result.Error(Throwable()),
            Result.Success(list)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(eventRepository.getNextFavoriteEvents()).thenReturn(expected[0])
                eventRepository.getNextFavoriteEvents()
                verify(eventRepository.getNextFavoriteEvents())
                Assert.assertEquals(eventRepository.getNextFavoriteEvents(), expected[0])
            }
        }
    }
}