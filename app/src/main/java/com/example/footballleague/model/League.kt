package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName("idLeague")
    val idLeague: String,

    @SerializedName("strLeague")
    val name: String,

    @SerializedName("strDescriptionEN")
    val description: String,

    @SerializedName("strCountry")
    val country: String,

    @SerializedName("strWebsite")
    val website: String,

    val path: Int
) : Parcelable