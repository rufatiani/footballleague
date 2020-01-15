package com.example.footballleague.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "FavoriteEvents.db", null, 8) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance =
                    DatabaseOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Const.TB_FAVORITE_EVENTS, true,
            Const.COL_ID_EVENT to TEXT + PRIMARY_KEY,
            Const.COL_ID_LEAGUE to TEXT,
            Const.COL_NAME_EVENT to TEXT,
            Const.COL_NAME_LEAGUE to TEXT,
            Const.COL_TYPE_SPORT to TEXT,
            Const.COL_HOME_TEAM to TEXT,
            Const.COL_AWAY_TEAM to TEXT,
            Const.COL_DATE_EVENT to TEXT,
            Const.COL_TIME_EVENT to TEXT,
            Const.COL_HOME_SCORE to INTEGER,
            Const.COL_AWAY_SCORE to INTEGER,
            Const.COL_HOME_FORMATION to TEXT,
            Const.COL_AWAY_FORMATION to TEXT,
            Const.COL_IMAGE_URL to TEXT
        )

        db.createTable(
            Const.TB_FAVORITE_TEAMS, true,
            Const.COL_ID_TEAM to TEXT + PRIMARY_KEY,
            Const.COL_NAME_TEAM to TEXT,
            Const.COL_ID_LEAGUE to TEXT,
            Const.COL_NAME_LEAGUE to TEXT,
            Const.COL_WEBSITE_URL to TEXT,
            Const.COL_YOUTUBE_URL to TEXT,
            Const.COL_COUNTRY to TEXT,
            Const.COL_TEAM_BADGE to TEXT,
            Const.COL_DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Const.TB_FAVORITE_EVENTS, true)
        db.dropTable(Const.TB_FAVORITE_TEAMS, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(
        applicationContext
    )

