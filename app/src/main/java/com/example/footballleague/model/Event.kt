package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("idEvent")
    val idEvent: String,

    @SerializedName("strEvent")
    val nameEvent: String,

    @SerializedName("idLeague")
    val idLeague: String,

    @SerializedName("strLeague")
    val nameLeague: String,

    @SerializedName("strHomeTeam")
    val homeTeam: String,

    @SerializedName("strAwayTeam")
    val awayTeam: String,

    @SerializedName("dateEvent")
    val date: String,

    @SerializedName("intHomeScore")
    val homeScore: Int,

    @SerializedName("intAwayScore")
    val awayScore: Int,

    @SerializedName("strHomeFormation")
    val homeFormation: String,

    @SerializedName("strAwayFormation")
    val awayFormation: String,

    var path: Int
) : Parcelable