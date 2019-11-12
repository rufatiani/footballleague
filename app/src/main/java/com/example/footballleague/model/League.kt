package com.example.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class League(val id: String, val name: String, val decsription: String, val path: Int) :
    Parcelable