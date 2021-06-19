package com.example.bridal.ui.homefragment
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding        : FragmentHomeBinding
    private val homeViewModel   : HomeViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
             return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with viewModel
        binding.lifecycleOwner  = this
        binding.homeFragment    = homeViewModel


        // setup for menu on actionBar from fragment.
        setHasOptionsMenu(true)

        // call function home list for category.
        homeViewModel.homeListItem(binding.listView, requireActivity())
    }

    // fun setup menu.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // operation work for click on menu items.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_setting -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsAccountFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


