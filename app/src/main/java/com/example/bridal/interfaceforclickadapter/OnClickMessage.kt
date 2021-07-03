package com.example.bridal.interfaceforclickadapter

import com.example.bridal.adapter.UserAdapter
import com.example.bridal.model.UserModel

interface OnClickMessage {

    fun onClickMessage( viewHolder: UserAdapter.ViewHolder , userModel: UserModel , position: Int)
}