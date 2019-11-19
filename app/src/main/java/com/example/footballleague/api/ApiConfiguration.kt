package com.example.footballleague.api

object ApiConfiguration {
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
    const val PATH_LEAGUE_DETAIL = "lookupleague.php" //id={idLeague}
    const val PATH_NEXT_MATCH = "eventsnextleague.php" //id={idLeague}
    const val PATH_PREVIOUS_MATCH = "eventspastleague.php" //id={idLeague}
    const val PATH_EVENT_DETAIL = "lookupevent.php" //id={idEvent}
    const val PATH_SEARCH_EVENT = "searchevents.php" //e={query}

    const val QUERY_ID = "id"
    const val QUERY_SEARCH = "e"
}