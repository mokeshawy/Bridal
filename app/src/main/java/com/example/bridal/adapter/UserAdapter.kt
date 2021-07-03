package com.example.bridal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.R
import com.example.bridal.databinding.ItemListMessageBinding
import com.example.bridal.interfaceforclickadapter.OnClickMessage
import com.example.bridal.model.UserModel
import com.example.bridal.ui.glideLoader

class UserAdapter(private val dataSet: ArrayList<UserModel>,
                  var onClick: OnClickMessage) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemListMessageBinding ) : RecyclerView.ViewHolder(binding.root) {

        fun initialize(viewHolder: ViewHolder , userModel: UserModel , action : OnClickMessage){
            action.onClickMessage(viewHolder , userModel , adapterPosition)
        }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(ItemListMessageBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.tvUserName.text = "${dataSet[position].firstName} ${dataSet[position].lastName}"

        // when user not select image profile will show default image
        if( dataSet[position].image == ""){
            viewHolder.binding.ivUserImage.setImageResource(R.drawable.default_image_profile)
        }else{
            // when user select image profile will show image
            glideLoader(viewHolder.itemView.context).loadUserPicture(dataSet[position].image,viewHolder.binding.ivUserImage)
        }

        viewHolder.initialize( viewHolder , dataSet[position] , onClick)
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}