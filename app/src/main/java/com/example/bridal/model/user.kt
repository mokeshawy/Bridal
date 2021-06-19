package com.example.bridal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class user (

        val id          : String ="",
        val firstname   : String= "" ,
        val lastname    : String= "",
        val email       : String= "",
        val password    : String = " ",
        val image       : String= "",
        val mobile      : Long = 0,
        val gender      :String="",
        val profilecompleted : Int = 0

):Parcelable
