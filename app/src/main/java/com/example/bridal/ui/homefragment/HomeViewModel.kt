package com.example.bridal.ui.homefragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.R
import com.example.bridal.adapter.HomeAdapter
import com.example.bridal.model.HomeListModel

class HomeViewModel : ViewModel() {


    // function for list category.
    fun homeListItem( listView : RecyclerView , context: Context){
        val list = mutableListOf<HomeListModel>()
        list.add(
            HomeListModel(context.resources.getString(R.string.beauty_center),
                R.drawable.beauty_center)
        )
        list.add(
            HomeListModel(context.resources.getString(R.string.buffet_services),
                R.drawable.buffet_services)
        )
        list.add(HomeListModel(context.resources.getString(R.string.car_rental), R.drawable.car_rental))
        list.add(
            HomeListModel(context.resources.getString(R.string.cosmetic_dentistry),
                R.drawable.cosmetic_dentistry)
        )
        list.add(
            HomeListModel(context.resources.getString(R.string.domestic_flights),
                R.drawable.domestic_flights)
        )
        list.add(HomeListModel(context.resources.getString(R.string.flowers), R.drawable.flowers))
        list.add(
            HomeListModel(context.resources.getString(R.string.gold_and_jewelry),
                R.drawable.gold_and_jewelry)
        )
        list.add(
            HomeListModel(context.resources.getString(R.string.health_and_beauty),
                R.drawable.health_and_beauty)
        )
        list.add(HomeListModel(context.resources.getString(R.string.honey_moon), R.drawable.honey_moon))
        list.add(HomeListModel(context.resources.getString(R.string.hotels), R.drawable.hotels))
        list.add(
            HomeListModel(context.resources.getString(R.string.watches_and_accessories),
                R.drawable.watches_and_accessories)
        )
        list.add(
            HomeListModel(context.resources.getString(R.string.wedding_group),
                R.drawable.wedding_gruop)
        )
        list.add(
            HomeListModel(context.resources.getString(R.string.wedding_halls),
                R.drawable.wedding_halls)
        )
        list.add(HomeListModel(context.resources.getString(R.string.wedding_suit), R.drawable.wedding_suit))
        list.add(
            HomeListModel(context.resources.getString(R.string.weeding_dresses),
                R.drawable.weeding_dresses)
        )
        listView.adapter = HomeAdapter(list)
    }
}