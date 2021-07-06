package com.example.bridal.ui.messagefragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.adapter.UserAdapter
import com.example.bridal.databinding.FragmentMessageBinding
import com.example.bridal.interfaceforclickadapter.OnClickMessage
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants


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
        messageViewModel.getChatList(requireActivity(),binding.rvMessage,binding.tvMessageNotFound)
        messageViewModel.mUsersLiveData.observe(viewLifecycleOwner , Observer {
            binding.rvMessage.adapter = UserAdapter(it , this)
        })
    }

    override fun onClickMessage(
        viewHolder: UserAdapter.ViewHolder,
        userModel: UserModel,
        position: Int
    ) {

        // get last message.
        messageViewModel.retrieveLastMessage(requireActivity(),userModel.userId,viewHolder.binding.tvLastMessage)

        // go to chat page.
        viewHolder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(Constants.USER_MODEL_KEY,userModel)
            findNavController().navigate(R.id.action_messageFragment_to_chatFragment,bundle)
        }
    }
}