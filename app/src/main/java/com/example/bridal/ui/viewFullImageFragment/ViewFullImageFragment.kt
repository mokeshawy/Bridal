package com.example.bridal.ui.viewFullImageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bridal.databinding.FragmentViewFullImageBinding
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants

class ViewFullImageFragment : Fragment() {

    lateinit var binding : FragmentViewFullImageBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewFullImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show full photo from details product form my add page.
        if( arguments?.containsKey(Constants.IMAGE_FROM_MY_ADD_DETAILS) == true){
            val image = arguments?.getString(Constants.IMAGE_FROM_MY_ADD_DETAILS)
            glideLoader(requireActivity()).loadUserPicture(image.toString(),binding.fullImage)
        }
        
        // show full image for product from product details for home page.
        if(arguments?.containsKey(Constants.IMAGE_FROM_HOME_DETAILS) == true){
            val image = arguments?.getString(Constants.IMAGE_FROM_HOME_DETAILS)
            glideLoader(requireActivity()).loadUserPicture(image.toString(),binding.fullImage)
        }
    }
}