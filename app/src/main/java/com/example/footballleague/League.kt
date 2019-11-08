package com.example.footballleague

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize


@Parcelize
data class League(val id : String, val name : String, val decsription : String, val path : Int) : Parcelable {}