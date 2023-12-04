package com.example.test2

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val Name:String
):Parcelable
