package com.example.bridal.interfaceforclickadapter

import com.example.bridal.adapter.FavoriteAdapter
import com.example.bridal.model.ProductModel
import java.text.FieldPosition

interface OnClickFavoriteAdapter {

    fun onClickFavorite( viewHolder: FavoriteAdapter.ViewHolder , productModel: ProductModel , position: Int)
}