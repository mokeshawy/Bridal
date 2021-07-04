package com.example.bridal.ui.secondscreenfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bridal.R
import com.example.bridal.databinding.FragmentSecondScreenBinding


class SecondScreenFragment : Fragment() {

    lateinit var binding : FragmentSecondScreenBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // second screen page.
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.next2.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }
}