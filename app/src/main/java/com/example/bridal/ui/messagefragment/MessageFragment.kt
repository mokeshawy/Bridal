package com.example.bridal.ui.messagefragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bridal.adapter.UserAdapter
import com.example.bridal.databinding.FragmentMessageBinding
import com.example.bridal.interfaceforclickadapter.OnClickMessage
import com.example.bridal.model.UserModel


class MessageFragment : Fragment() , OnClickMessage {

    lateinit var binding            : FragmentMessageBinding
    private val messageViewModel    : MessageViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner = this
        binding.messageFragment = messageViewModel

        // get all data for user from data base
        messageViewModel.getUser(requireActivity())
        messageViewModel.mUserLiveArrayList.observe(viewLifecycleOwner , Observer {
            binding.rvMessage.adapter = UserAdapter(it , this)
            if(it.size >0){
                binding.rvMessage.visibility = View.VISIBLE
                binding.tvMessageNotFound.visibility = View.GONE
            }else{
                binding.rvMessage.visibility = View.GONE
                binding.tvMessageNotFound.visibility = View.VISIBLE
            }
        })


    }

    override fun onClickMessage(
        viewHolder: UserAdapter.ViewHolder,
        userModel: UserModel,
        position: Int
    ) {

    }
}