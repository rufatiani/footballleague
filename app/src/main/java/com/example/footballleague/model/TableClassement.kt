package com.example.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TableClassement(
    @SerializedName("table")
    val classements: List<Classement>
) : Parcelable