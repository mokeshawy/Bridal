package com.example.bridal.ui.homefragment
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bridal.R
import com.example.bridal.adapter.HomeAdapter
import com.example.bridal.databinding.FragmentHomeBinding
import com.example.bridal.interfaceforclickadapter.OnClickHomeAdapter
import com.example.bridal.model.HomeListModel
import com.example.bridal.ui.glideLoader

class HomeFragment : Fragment() , OnClickHomeAdapter{

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

        // operation work for carouseView.
        val image = intArrayOf(R.drawable.image_one,
            R.drawable.image_tow,
            R.drawable.image_three)
        val cities = arrayOf("1","2","3")
        binding.carouseView.setImageListener { position, imageView ->
            glideLoader(requireActivity()).loadUserPicture(image[position],imageView)
        }
        binding.carouseView.pageCount = cities.size

        // call function home list for category.
        homeViewModel.homeListItem(requireActivity())
        homeViewModel.homeListAdapter.observe(viewLifecycleOwner, Observer {
            binding.loadingView.visibility = View.VISIBLE
            binding.rvCategoryList.adapter = HomeAdapter(it,this)
            binding.loadingView.visibility = View.GONE
        })
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

    override fun onClickHomListItem( viewHolder: HomeAdapter.ViewHolder,
                                     homeListModel: HomeListModel,
                                     position: Int ) {

        // entry to product from category list.
        viewHolder.itemView.setOnClickListener {
           homeViewModel.entryToProduct(requireActivity(),viewHolder.itemView,position)
        }
    }
}


