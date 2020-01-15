package com.example.footballleague.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.footballleague.api.ApiInterface
import com.example.footballleague.utils.database
import com.example.footballleague.model.Event
import com.example.footballleague.model.Events
import com.example.footballleague.model.EventsJson
import com.example.footballleague.utils.Const
import com.example.footballleague.utils.Result
import org.jetbrains.anko.db.*
import java.text.SimpleDateFormat
import java.util.*

interface EventRepository {
    suspend fun getEvents(query: String): Result<Events>

    suspend fun getPrevEvents(query: String): Result<EventsJson>

    suspend fun getNextEvents(query: String): Result<EventsJson>

    fun saveFavoriteEvent(event: Event): Result<Long>

    fun deleteFavoriteEvent(idEvent: String): Result<Int>

    suspend fun getPrevFavoriteEvents(): Result<List<Event>>

    suspend fun getNextFavoriteEvents(): Result<List<Event>>
}

class EventRepositoryImpl(private val apiInterface: ApiInterface, val context: Context) :
    EventRepository {
    override suspend fun getEvents(query: String): Result<Events> {
        return try {
            val result = apiInterface.getListEvent(query).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPrevEvents(query: String): Result<EventsJson> {
        return try {
            val result = apiInterface.getPrevEvent(query).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getNextEvents(query: String): Result<EventsJson> {
        return try {
            val result = apiInterface.getNextEvent(query).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun saveFavoriteEvent(event: Event): Result<Long> {
        return try {
            var result: Long = 0
            context.database.use {
                result =
                    insert(
                        Const.TB_FAVORITE_EVENTS,
                        Const.COL_ID_EVENT to event.idEvent,
                        Const.COL_ID_LEAGUE to event.idLeague,
                        Const.COL_NAME_EVENT to event.nameEvent,
                        Const.COL_NAME_LEAGUE to event.nameLeague,
                        Const.COL_TYPE_SPORT to event.sport,
                        Const.COL_HOME_TEAM to event.homeTeam,
                        Const.COL_AWAY_TEAM to event.awayTeam,
                        Const.COL_DATE_EVENT to event.date,
                        Const.COL_TIME_EVENT to event.time,
                        Const.COL_HOME_SCORE to event.homeScore,
                        Const.COL_AWAY_SCORE to event.awayScore,
                        Const.COL_HOME_FORMATION to event.homeFormation,
                        Const.COL_AWAY_FORMATION to event.awayFormation,
                        Const.COL_IMAGE_URL to event.imageUrl
                    )
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun deleteFavoriteEvent(idEvent: String): Result<Int> {
        return try {
            val result = context.database.use {
                delete(
                    Const.TB_FAVORITE_EVENTS, "( " + Const.COL_ID_EVENT + " = {idEvent})",
                    "idEvent" to idEvent
                )
            }
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun getPrevFavoriteEvents(): Result<List<Event>> {
        return try {
            context.database.use {
                select(Const.TB_FAVORITE_EVENTS)
                    .whereArgs(
                        "( " + Const.COL_DATE_EVENT + " <= {today})",
                        "today" to SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date())
                    ).exec {
                        Result.Success(this.parseList(classParser<Event>()))
                    }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun getNextFavoriteEvents(): Result<List<Event>> {
        return try {
            context.database.use {
                select(Const.TB_FAVORITE_EVENTS)
                    .whereArgs(
                        "( " + Const.COL_DATE_EVENT + " > {today})",
                        "today" to SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date())
                    ).exec {
                        Result.Success(this.parseList(classParser<Event>()))
                    }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}