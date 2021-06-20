package com.example.bridal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.databinding.HomeItemListBinding
import com.example.bridal.interfaceforclickadapter.OnClickHomeAdapter
import com.example.bridal.model.HomeListModel
import com.example.bridal.model.ProductModel
import com.example.bridal.ui.glideLoader

class HomeAdapter(private var mHomeList: List<HomeListModel> , var onClickHomeAdapter: OnClickHomeAdapter ) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(var binding : HomeItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        // initialize onClickUsersAdapter from interface
        fun initialize(viewHolder: ViewHolder, mHomeList: HomeListModel , action : OnClickHomeAdapter){
            action.onClickHomListItem(viewHolder , mHomeList , adapterPosition )
        }

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(HomeItemListBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.title.text = mHomeList[position].title
        glideLoader(viewHolder.itemView.context).loadUserPicture(mHomeList[position].image,viewHolder.binding.icon)

        viewHolder.initialize( viewHolder , mHomeList[position] , onClickHomeAdapter)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mHomeList.size

}