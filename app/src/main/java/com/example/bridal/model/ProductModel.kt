package com.example.bridal.model

import java.io.Serializable

data class ProductModel(

    var userId          : String = "",
    var categoryName    : String = "",
    var productTitle    : String = "",
    var productPrice    : String = "",
    var productImage    : String = "",
    var productDescription : String = "",
    var latitude        : String = "",
    var longitude       : String = ""
) : Serializable