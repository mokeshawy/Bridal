package com.example.bridal.interfaceforclickadapter

import com.example.bridal.adapter.HomeAdapter
import com.example.bridal.model.HomeListModel
import com.example.bridal.model.ProductModel

interface OnClickHomeAdapter {

    fun onClickHomListItem( viewHolder: HomeAdapter.ViewHolder , homeListModel: HomeListModel , position : Int)
}