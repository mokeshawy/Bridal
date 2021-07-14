package com.example.bridal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.databinding.ProductItemListBinding
import com.example.bridal.interfaceforclickadapter.OnClickProductAdapter
import com.example.bridal.model.ProductModel
import com.example.bridal.ui.glideLoader

class ProductAdapter (private var mProductList: ArrayList<ProductModel>
, var onClickProduct: OnClickProductAdapter) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(var binding : ProductItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        // initialize onClickUsersAdapter from interface
        fun initialize(viewHolder: ViewHolder, mProductList : ProductModel , action : OnClickProductAdapter){
            action.onClickProduct(viewHolder , mProductList , adapterPosition )
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

        val product = mProductList[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.apply {
            tvProductTitle.text          = product.productTitle
            tvProductDescription.text    = product.productDescription
            tvProductPrice.text          = "$${product.productPrice}"
            glideLoader(viewHolder.itemView.context).loadUserPicture(product.productImageOne,ivIconProduct)

        }

//        glideLoader(viewHolder.itemView.context).loadUserPicture(mHomeList[position].image,viewHolder.binding.icon)

        viewHolder.initialize( viewHolder , mProductList[position] , onClickProduct)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mProductList.size

}