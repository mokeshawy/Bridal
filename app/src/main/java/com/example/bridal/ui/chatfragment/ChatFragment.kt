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
import com.example.bridal.util.Constants

class ChatFragment : Fragment() {

    lateinit var binding : FragmentChatBinding
    private val chatViewModel : ChatViewModel by viewModels()
    var mProductDetails : ProductModel? = null
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


        if(arguments?.containsKey(Constants.PRODUCT_CHAT_OBJECT) == true){

            mProductDetails =  arguments?.getSerializable(Constants.PRODUCT_CHAT_OBJECT) as ProductModel

            binding.apply {
                tvUserNameChat.text = mProductDetails!!.userName
                ivSendMessageBtn.setOnClickListener {

                    chatViewModel.sendMessage(mProductDetails!!.userId,view,etTextMessage)
                }

                chatViewModel.readMessage(requireActivity(),mProductDetails!!.userId)
                chatViewModel.mGetMessageLiveList.observe(viewLifecycleOwner, Observer {
                    recyclerViewChats.adapter = ChatAdapter(it)
                })
            }
        }


    }
}