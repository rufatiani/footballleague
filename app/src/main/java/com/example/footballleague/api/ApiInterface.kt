package com.example.footballleague.api

import com.example.footballleague.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConfiguration.PATH_LEAGUE_DETAIL)
    fun getDetailLeague(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<Leagues>

    @GET(ApiConfiguration.PATH_EVENT_DETAIL)
    fun getDetailEvent(@Query(ApiConfiguration.QUERY_ID) idEvent: String): Deferred<Event>

    @GET(ApiConfiguration.PATH_NEXT_MATCH)
    fun getNextEvent(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<EventsJson>

    @GET(ApiConfiguration.PATH_PREVIOUS_MATCH)
    fun getPrevEvent(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<EventsJson>

    @GET(ApiConfiguration.PATH_SEARCH_EVENT)
    fun getListEvent(@Query(ApiConfiguration.QUERY_SEARCH) query: String): Deferred<Events>

}