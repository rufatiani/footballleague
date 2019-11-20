package com.example.footballleague.repository

import com.example.footballleague.api.ApiInterface
import com.example.footballleague.model.Events
import com.example.footballleague.model.EventsJson
import com.example.footballleague.utils.Result

interface EventRepository {
    suspend fun getEvents(query: String): Result<Events>

    suspend fun getPrevEvents(query: String): Result<EventsJson>

    suspend fun getNextEvents(query: String): Result<EventsJson>
}

class EventRepositoryImpl(private val apiInterface: ApiInterface) : EventRepository {

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

}