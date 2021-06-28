package com.example.bridal.model

import java.io.Serializable

data class ProductModel(

    var userId              : String = "",
    var userName            : String = "",
    var productId           : String = "",
    var categoryName        : String = "",
    var productTitle        : String = "",
    var productPrice        : String = "",
    var productImageOne     : String = "",
    var productImageTow     : String = "",
    var ProductImageThree   : String = "",
    var productVideo        : String = "",
    var productDescription  : String = "",
    var latitude            : String = "",
    var longitude           : String = ""

) : Serializable