package com.example.bridal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class ProductModel(

    @ColumnInfo( name = "userId")
    var userId              : String = "",
    @ColumnInfo( name = "userName")
    var userName            : String = "",
    @ColumnInfo( name = "categoryPosition")
    var categoryPosition    : String = "",
    @ColumnInfo( name = "categoryName")
    var categoryName        : String = "",
    @ColumnInfo( name = "productTitle")
    var productTitle        : String = "",
    @ColumnInfo( name = "productPrice")
    var productPrice        : String = "",
    @ColumnInfo( name = "productImageOne")
    var productImageOne     : String = "",
    @ColumnInfo( name = "productImageTow")
    var productImageTow     : String = "",
    @ColumnInfo( name = "productImageThree")
    var productImageThree   : String = "",
    @ColumnInfo( name = "productImageFour")
    var productImageFour    : String = "",
    @ColumnInfo( name = "productImageFive")
    var productImageFive    : String = "",
    @ColumnInfo( name = "productImageSix")
    var productImageSix     : String = "",
    @ColumnInfo( name = "productVideo")
    var productVideo        : String = "",
    @ColumnInfo( name = "productVideoTwo")
    var productVideoTwo        : String = "",
    @ColumnInfo( name = "productVideoThree")
    var productVideoThree        : String = "",
    @ColumnInfo( name = "productDescription")
    var productDescription  : String = "",
    @ColumnInfo( name = "latitude")
    var latitude            : String = "",
    @ColumnInfo( name = "longitude")
    var longitude           : String = "",
    @ColumnInfo(name = "pushKey")
    var pushKey             : String = "",
    @ColumnInfo(name = "rate")
    var rate                :Int = 0

) : Serializable
{
    @PrimaryKey( autoGenerate = true)
    var id : Int = 0
}