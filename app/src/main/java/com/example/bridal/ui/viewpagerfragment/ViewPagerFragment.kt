package com.example.bridal.ui.viewpagerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bridal.ui.thirdscreenfragment.ThirdScreenFragment
import com.example.bridal.ui.firstscreenfragment.FirstScreenFragment
import com.example.bridal.ui.secondscreenfragment.SecondScreenFragment
import com.example.bridal.adapter.ViewPagerAdapter
import com.example.bridal.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    lateinit var binding : FragmentViewPagerBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = arrayListOf<Fragment>(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
    }
}