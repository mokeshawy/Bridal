package com.example.bridal.model

import java.io.Serializable

class UserModel (

        val userId              : String = "",
        val firstName           : String = "" ,
        val lastName            : String = "",
        val email               : String = "",
        val image               : String = "",
        val mobile              : String = "",
        val country             : String = "",
        val gender              : String = "",
        val profileCompleted    : Int    = 0,
        val userPremium         : Int    = 0,

) : Serializable
