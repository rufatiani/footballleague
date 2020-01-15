package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    @SerializedName("teams")
    val teams: List<Team>
) : Parcelable