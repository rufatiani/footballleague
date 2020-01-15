package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Classement(
    @SerializedName("name")
    val name: String,

    @SerializedName("teamid")
    val idTeam: String,

    @SerializedName("win")
    val win: Int,

    @SerializedName("draw")
    val draw: Int,

    @SerializedName("loss")
    val loss: Int
) : Parcelable