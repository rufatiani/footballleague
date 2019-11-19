package com.example.footballleague.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FootballLeagueApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FootballLeagueApplication)
            modules(appModules)
        }
    }
}