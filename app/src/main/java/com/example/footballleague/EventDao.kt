//package com.example.footballleague
//
//import android.annotation.SuppressLint
//import com.example.footballleague.model.Event
//import kotlinx.coroutines.Deferred
//import org.jetbrains.anko.db.delete
//import org.jetbrains.anko.db.insert
//import org.jetbrains.anko.db.select
//import java.text.SimpleDateFormat
//import java.util.*
//
//interface EventDao{
//    fun addEvent(event : Event) : Long
//
//    fun deleteEvent(idEvent : String) : Int
//
//    fun getPrevEvents() : Deferred<List<Event>>?
//
//    fun getNextEvents() : Deferred<List<Event>>?
//}
//
//class EventDaoImpl(val database: DatabaseOpenHelper) : EventDao{
//    override fun addEvent(event: Event) : Long {
//
//          return database.use {
//                insert("FAVORITE_EVENTS",
//                    "ID_EVENT" to event.idEvent,
//                    "ID_LEAGUE" to event.idLeague,
//                    "NAME_EVENT" to event.nameEvent,
//                    "NAME_LEAGUE" to event.nameLeague,
//                    "SPORT_TYPE" to event.sport,
//                    "HOME_TEAM" to event.homeTeam,
//                    "AWAY_TEAM" to event.awayTeam,
//                    "DATE_EVENT" to event.date,
//                    "TIME_EVENT" to event.time,
//                    "HOME_SCORE" to event.homeScore,
//                    "AWAY_SCORE" to event.awayScore,
//                    "HOME_FORMATION" to event.homeFormation,
//                    "AWAY FORMATION" to event.awayFormation,
//                    "IMAGE_URL" to event.imageUrl)
//            }
//    }
//
//    override fun deleteEvent(idEvent: String) : Int {
//          return  database.use {
//                delete("FAVORITE_EVENTS", "(ID_EVENT = {idEvent})",
//                    "idEvent" to idEvent)
//            }
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    override fun getPrevEvents() : Deferred<List<Event>>? {
//        database.use {
//            select("FAVORITE_EVENTS")
//                .whereArgs(
//                    "(DATE_EVENT <= {today})",
//                    "today" to SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date())
//                ).exec {
//                   return@exec
//                }
//        }
//
//        return null;
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    override fun getNextEvents() : Deferred<List<Event>>? {
//        database.use {
//            select("FAVORITE_EVENTS")
//                .whereArgs(
//                    "(DATE_EVENT > {today})",
//                    "today" to SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date())
//                ).exec {
//                    return@exec
//                }
//        }
//
//        return null;
//    }
//}