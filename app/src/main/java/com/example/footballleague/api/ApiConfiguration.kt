package com.example.footballleague.api

object ApiConfiguration {
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
    const val PATH_LEAGUE_DETAIL = "lookupleague.php"
    const val PATH_NEXT_MATCH = "eventsnextleague.php"
    const val PATH_PREVIOUS_MATCH = "eventspastleague.php"
    const val PATH_EVENT_DETAIL = "lookupevent.php"
    const val PATH_SEARCH_EVENT = "searchevents.php"
    const val PATH_TEAM_LIST = "lookup_all_teams.php"
    const val PATH_TEAM_DETAIL = "lookupteam.php"
    const val PATH_SEARCH_TEAM = "searchteams.php"
    const val PATH_CLASSEMENT = "lookuptable.php"

    const val QUERY_ID = "id"
    const val QUERY_SEARCH = "e"
    const val QUERY_SEARCH_TEAM = "t"
    const val QUERY_LEAGUE = "l"
}