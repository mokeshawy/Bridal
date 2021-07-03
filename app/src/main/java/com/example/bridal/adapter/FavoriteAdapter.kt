package com.example.bridal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.databinding.ProductItemListBinding
import com.example.bridal.interfaceforclickadapter.OnClickFavoriteAdapter
import com.example.bridal.interfaceforclickadapter.OnClickProductAdapter
import com.example.bridal.model.ProductModel

class FavoriteAdapter (private var mProductList: ArrayList<ProductModel>
                       , var onClickFavorite: OnClickFavoriteAdapter
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(var binding : ProductItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        // initialize onClickUsersAdapter from interface
        fun initialize(viewHolder: ViewHolder, mProductList : ProductModel, action : OnClickFavoriteAdapter){
            action.onClickFavorite(viewHolder , mProductList , adapterPosition )
        }

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(ProductItemListBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.apply {
            tvProductTitle.text          = mProductList[position].productTitle
            tvProductDescription.text    = mProductList[position].productDescription
            tvProductPrice.text          = "$${mProductList[position].productPrice}"
        }

//        glideLoader(viewHolder.itemView.context).loadUserPicture(mHomeList[position].image,viewHolder.binding.icon)

        viewHolder.initialize( viewHolder , mProductList[position] , onClickFavorite)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mProductList.size


    fun update( newData : List<ProductModel>){
        mProductList.clear()
        mProductList.addAll(newData)
        notifyDataSetChanged()
    }
}