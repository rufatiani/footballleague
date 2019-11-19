package com.example.footballleague.api

import com.example.footballleague.model.Event
import com.example.footballleague.model.League
import com.example.footballleague.model.Leagues
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConfiguration.PATH_LEAGUE_DETAIL)
    fun getDetailLeague(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<Leagues>

    @GET(ApiConfiguration.PATH_EVENT_DETAIL)
    fun getDetailEvent(@Path(ApiConfiguration.QUERY_ID) idEvent: String): Deferred<Event>

    @GET(ApiConfiguration.PATH_NEXT_MATCH)
    fun getNextEvent(@Path(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<List<Event>>

}