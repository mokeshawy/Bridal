package com.example.bridal.ui.homefragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.bridal.R
import com.example.bridal.adapter.HomeAdapter
import com.example.bridal.databinding.FragmentHomeBinding
import com.example.bridal.model.HomeListModel

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
             return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup for menu on actionBar from fragment.
        setHasOptionsMenu(true)

        // call function home list for category.
        homeListItem()
    }

    // fun setup mene.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // operation work for click on menu items.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_setting -> {
                //startActivity(Intent(activity, settings::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // function for list category.
    private fun homeListItem(){
        val list = mutableListOf<HomeListModel>()
        list.add(HomeListModel(resources.getString(R.string.beauty_center),R.drawable.beauty_center))
        list.add(HomeListModel(resources.getString(R.string.buffet_services),R.drawable.buffet_services))
        list.add(HomeListModel(resources.getString(R.string.car_rental),R.drawable.car_rental))
        list.add(HomeListModel(resources.getString(R.string.cosmetic_dentistry),R.drawable.cosmetic_dentistry))
        list.add(HomeListModel(resources.getString(R.string.domestic_flights),R.drawable.domestic_flights))
        list.add(HomeListModel(resources.getString(R.string.flowers),R.drawable.flowers))
        list.add(HomeListModel(resources.getString(R.string.gold_and_jewelry),R.drawable.gold_and_jewelry))
        list.add(HomeListModel(resources.getString(R.string.health_and_beauty),R.drawable.health_and_beauty))
        list.add(HomeListModel(resources.getString(R.string.honey_moon),R.drawable.honey_moon))
        list.add(HomeListModel(resources.getString(R.string.hotels),R.drawable.hotels))
        list.add(HomeListModel(resources.getString(R.string.watches_and_accessories),R.drawable.watches_and_accessories))
        list.add(HomeListModel(resources.getString(R.string.wedding_group),R.drawable.wedding_gruop))
        list.add(HomeListModel(resources.getString(R.string.wedding_halls),R.drawable.wedding_halls))
        list.add(HomeListModel(resources.getString(R.string.wedding_suit),R.drawable.wedding_suit))
        list.add(HomeListModel(resources.getString(R.string.weeding_dresses),R.drawable.weeding_dresses))

        binding.listView.adapter = HomeAdapter(list)
    }
}


