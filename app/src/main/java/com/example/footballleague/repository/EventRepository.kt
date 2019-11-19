package com.example.footballleague.repository

import com.example.footballleague.api.ApiInterface
import com.example.footballleague.model.Events
import com.example.footballleague.utils.Result

interface EventRepository {
    suspend fun getEvents(query : String) : Result<Events>
}

class EventRepositoryImpl (private val apiInterface: ApiInterface) : EventRepository{

    override suspend fun getEvents(query: String): Result<Events> {
        return try {
            val result = apiInterface.getListEvent(query).await()
            Result.Success(result)
        }catch (e : Exception){
            Result.Error(e)
        }
    }

}