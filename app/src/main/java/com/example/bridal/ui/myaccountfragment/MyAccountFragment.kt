package com.example.bridal.ui.myaccountfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.bridal.R
import com.example.bridal.databinding.FragmentMyAccountBinding

class MyAccountFragment : Fragment() {

    lateinit var binding : FragmentMyAccountBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMyAccount.text = "My Account"
    }
}