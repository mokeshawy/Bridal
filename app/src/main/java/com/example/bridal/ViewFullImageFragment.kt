package com.example.bridal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bridal.databinding.FragmentViewFullImageBinding
import com.example.bridal.ui.glideLoader

class ViewFullImageFragment : Fragment() {

    lateinit var binding : FragmentViewFullImageBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewFullImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if( arguments?.containsKey("image1") == true){
            val image = arguments?.getString("image1")
            glideLoader(requireActivity()).loadUserPicture(image.toString(),binding.fullImage)
        }


        if(arguments?.containsKey("image") == true){
            val image = arguments?.getString("image")
            glideLoader(requireActivity()).loadUserPicture(image.toString(),binding.fullImage)
        }
    }
}