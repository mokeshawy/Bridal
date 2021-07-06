package com.example.bridal.ui.chatfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bridal.adapter.ChatAdapter
import com.example.bridal.databinding.FragmentChatBinding
import com.example.bridal.model.ProductModel
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants

class ChatFragment : Fragment() {

    lateinit var binding : FragmentChatBinding
    private val chatViewModel : ChatViewModel by viewModels()
    var mProductDetails : ProductModel? = null
    var mUserDetails    : UserModel?    = null
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel.
        binding.lifecycleOwner  = this
        binding.chatFragment    = chatViewModel

        // when user entry to chat form details page.
        if(arguments?.containsKey(Constants.PRODUCT_CHAT_OBJECT) == true){
            mProductDetails =  arguments?.getSerializable(Constants.PRODUCT_CHAT_OBJECT) as ProductModel
            binding.apply {
                tvUserNameChat.text = mProductDetails!!.userName
                ivSendMessageBtn.setOnClickListener {

                    chatViewModel.sendMessage(mProductDetails!!.userId,view,etTextMessage)
                }

                chatViewModel.retrieveMessage(requireActivity(),Constants.getCurrentUser(),mProductDetails!!.userId)
                chatViewModel.mChatArrayListLive.observe(viewLifecycleOwner, Observer {
                    recyclerViewChats.adapter = ChatAdapter(it)
                })
            }
        }

        // when user entry to chat from my message.
        if(arguments?.containsKey(Constants.USER_MODEL_KEY) == true){
            mUserDetails = arguments?.getSerializable(Constants.USER_MODEL_KEY) as UserModel
            binding.apply {
                tvUserNameChat.text = mUserDetails!!.firstName
                ivSendMessageBtn.setOnClickListener {
                    chatViewModel.sendMessage(mUserDetails!!.userId,view,etTextMessage)
                }

                chatViewModel.retrieveMessage(requireActivity(),Constants.getCurrentUser(),mUserDetails!!.userId)
                chatViewModel.mChatArrayListLive.observe(viewLifecycleOwner, Observer {
                    recyclerViewChats.adapter = ChatAdapter(it)
                })
            }
        }
    }
}