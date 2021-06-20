package com.example.bridal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.databinding.ProductItemListBinding
import com.example.bridal.model.ProductModel
import com.example.bridal.ui.glideLoader

class ProductAdapter (private var mProductList: ArrayList<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(var binding : ProductItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        // initialize onClickUsersAdapter from interface
//        fun initialize(viewHolder: ViewHolder, mHomeList: HomeListModel, action : OnClickHomeAdapter){
//            action.onClickHomListItem(viewHolder , mHomeList , adapterPosition )
//        }

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(ProductItemListBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.tvProductTitle.text          = mProductList[position].productTitle
        viewHolder.binding.tvProductDescription.text    = mProductList[position].productDescription
        viewHolder.binding.tvProductPrice.text          = "${mProductList[position].productPrice} $"
//        glideLoader(viewHolder.itemView.context).loadUserPicture(mHomeList[position].image,viewHolder.binding.icon)

        //viewHolder.initialize( viewHolder , mHomeList[position] , onClickHomeAdapter)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mProductList.size

}