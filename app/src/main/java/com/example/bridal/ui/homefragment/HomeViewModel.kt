package com.example.bridal.ui.homefragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.R
import com.example.bridal.adapter.HomeAdapter
import com.example.bridal.model.HomeListModel
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants

class HomeViewModel : ViewModel() {

    var homeListAdapter = MutableLiveData<List<HomeListModel>>()

    // function for list category.
    fun homeListItem(context: Context){
        val list = mutableListOf<HomeListModel>()
        list.add( HomeListModel(context.resources.getString(R.string.beauty_center),R.drawable.beauty_center))
        list.add( HomeListModel(context.resources.getString(R.string.buffet_services),R.drawable.buffet_services))
        list.add(HomeListModel(context.resources.getString(R.string.car_rental), R.drawable.car_rental))
        list.add( HomeListModel(context.resources.getString(R.string.cosmetic_dentistry),R.drawable.cosmetic_dentistry))
        list.add( HomeListModel(context.resources.getString(R.string.domestic_flights),R.drawable.domestic_flights))
        list.add(HomeListModel(context.resources.getString(R.string.flowers), R.drawable.flowers))
        list.add( HomeListModel(context.resources.getString(R.string.gold_and_jewelry),R.drawable.gold_and_jewelry))
        list.add( HomeListModel(context.resources.getString(R.string.health_and_beauty),R.drawable.health_and_beauty))
        list.add(HomeListModel(context.resources.getString(R.string.honey_moon), R.drawable.honey_moon))
        list.add(HomeListModel(context.resources.getString(R.string.hotels), R.drawable.hotels))
        list.add( HomeListModel(context.resources.getString(R.string.watches_and_accessories),R.drawable.watches_and_accessories))
        list.add(HomeListModel(context.resources.getString(R.string.wedding_group),R.drawable.wedding_gruop))
        list.add(HomeListModel(context.resources.getString(R.string.wedding_halls), R.drawable.wedding_halls))
        list.add(HomeListModel(context.resources.getString(R.string.wedding_suit), R.drawable.wedding_suit))
        list.add(HomeListModel(context.resources.getString(R.string.weeding_dresses),R.drawable.weeding_dresses))

        homeListAdapter.value = list
    }

    fun entryToProduct( context: Context , view: View , homeListModel: HomeListModel){
        if(homeListModel.title == context.resources.getString(R.string.beauty_center) ){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.buffet_services)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.car_rental)){
            goProductPage(context,view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.cosmetic_dentistry)){
            goProductPage(context, view, homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.domestic_flights)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.flowers)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.gold_and_jewelry)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.health_and_beauty)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.honey_moon)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.hotels)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.watches_and_accessories)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.wedding_group)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.wedding_halls)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.wedding_suit)){
            goProductPage(context, view , homeListModel)
        }else if(homeListModel.title == context.resources.getString(R.string.weeding_dresses)){
            goProductPage(context, view , homeListModel)
        }
    }

    // fun for entry to product from category list.
    fun goProductPage( context: Context , view : View , homeListModel: HomeListModel){
        var bundle = bundleOf( Constants.PRODUCT_ITEM_KEY to homeListModel.title)
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productFragment,bundle)
    }
}