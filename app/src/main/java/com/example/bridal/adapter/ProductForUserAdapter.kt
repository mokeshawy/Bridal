package com.example.bridal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.databinding.ProductForUserListBinding
import com.example.bridal.interfaceforclickadapter.OnClickProductForUserAdapter
import com.example.bridal.model.ProductModel

class ProductForUserAdapter (private var mProductList: ArrayList<ProductModel> ,
                             var onClickProductForUser : OnClickProductForUserAdapter) : RecyclerView.Adapter<ProductForUserAdapter.ViewHolder>() {

    class ViewHolder(var binding : ProductForUserListBinding) : RecyclerView.ViewHolder(binding.root) {

        // initialize onClickUsersAdapter from interface
        fun initialize(viewHolder: ViewHolder, mProductList : ProductModel , action : OnClickProductForUserAdapter){
            action.onClickProductForUser(viewHolder , mProductList , adapterPosition )
        }

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(ProductForUserListBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.apply {
            tvProductTitle.text          = mProductList[position].productTitle
            tvProductPrice.text          = "$${mProductList[position].productPrice}"
            tvCategoryProduct.text       = mProductList[position].categoryName
        }

//        glideLoader(viewHolder.itemView.context).loadUserPicture(mHomeList[position].image,viewHolder.binding.icon)

        viewHolder.initialize( viewHolder , mProductList[position] , onClickProductForUser)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mProductList.size

}