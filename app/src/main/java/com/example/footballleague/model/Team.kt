package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @SerializedName("idTeam")
    val idTeam : String,

    @SerializedName("strTeam")
    val nameTeam : String,

    @SerializedName("idLeague")
    val idLeague : String,

    @SerializedName("strLeague")
    val nameLeague : String,

    @SerializedName("strWebsite")
    val website : String,

    @SerializedName("strYoutube")
    val youtube : String,

    @SerializedName("strCountry")
    val country : String,

    @SerializedName("strTeamBadge")
    val teamBadge : String,

    @SerializedName("strDescriptionEN")
    val description : String
): Parcelable