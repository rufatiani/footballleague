package com.example.footballleague.api

import com.example.footballleague.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConfiguration.PATH_LEAGUE_DETAIL)
    fun getDetailLeague(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<Leagues>

    @GET(ApiConfiguration.PATH_NEXT_MATCH)
    fun getNextEvent(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<EventsJson>

    @GET(ApiConfiguration.PATH_PREVIOUS_MATCH)
    fun getPrevEvent(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<EventsJson>

    @GET(ApiConfiguration.PATH_SEARCH_EVENT)
    fun getListEvent(@Query(ApiConfiguration.QUERY_SEARCH) query: String): Deferred<Events>

    @GET(ApiConfiguration.PATH_TEAM_LIST)
    fun getListTeam(@Query(ApiConfiguration.QUERY_ID) idLeague: String): Deferred<Teams>

    @GET(ApiConfiguration.PATH_SEARCH_TEAM)
    fun getListTeamSearch(@Query(ApiConfiguration.QUERY_SEARCH_TEAM) query: String): Deferred<Teams>

    @GET(ApiConfiguration.PATH_TEAM_DETAIL)
    fun getDetailTeam(@Query(ApiConfiguration.QUERY_ID) idTeam: String): Deferred<Teams>

    @GET(ApiConfiguration.PATH_CLASSEMENT)
    fun getListClassement(@Query(ApiConfiguration.QUERY_LEAGUE) idLeague: String): Deferred<TableClassement>

}