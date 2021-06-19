package com.example.bridal.ui.dashboardfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.bridal.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

  //  private lateinit var dashboardViewModel: DashboardViewModel
    lateinit var binding : FragmentDashboardBinding
    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setMenuVisibility(true)

        binding.textDashboard.text = "DashBoard"

    }
}