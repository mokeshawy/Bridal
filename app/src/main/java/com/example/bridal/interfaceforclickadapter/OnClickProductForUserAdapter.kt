package com.example.bridal.interfaceforclickadapter

import com.example.bridal.adapter.ProductForUserAdapter
import com.example.bridal.model.ProductModel

interface OnClickProductForUserAdapter {

    fun onClickProductForUser( viewHolder: ProductForUserAdapter.ViewHolder
                               , productModel : ProductModel
                               , position : Int)
}