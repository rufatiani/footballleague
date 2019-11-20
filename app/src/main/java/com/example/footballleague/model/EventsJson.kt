package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class EventsJson(
    @SerializedName("events")
    val list: List<Event>) : Parcelable