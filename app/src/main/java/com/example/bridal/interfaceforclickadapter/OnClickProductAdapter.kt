package com.example.bridal.interfaceforclickadapter

import com.example.bridal.adapter.ProductAdapter
import com.example.bridal.model.ProductModel
import java.text.FieldPosition

interface OnClickProductAdapter {

    fun onClickProduct( viewHolder: ProductAdapter.ViewHolder , product : ProductModel , position : Int)
}