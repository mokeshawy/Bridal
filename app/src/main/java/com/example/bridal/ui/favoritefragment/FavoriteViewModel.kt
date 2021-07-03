package com.example.bridal.ui.favoritefragment

import android.content.Context

import android.widget.ToggleButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bridal.model.ProductModel
import com.example.bridal.roomdb.DatabaseModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {




    fun selectFavorite(context: Context) : LiveData<List<ProductModel>>{
        return DatabaseModule.provideDatabase(context).bridalDao().selectAll().asLiveData()
    }


    // add product to room data base
    fun addProductToFavorite(context: Context, productModel: ProductModel){
        CoroutineScope(Dispatchers.IO).launch {
            val database = DatabaseModule.provideDatabase(context)
            CoroutineScope(Dispatchers.Main).launch{
                val pushKey = database.bridalDao().selectByPushKey(productModel.pushKey)
                if(pushKey.size == 1){

                }else{
                    database.bridalDao().insertProduct(productModel)
                }
            }
        }
    }

    // fun un favorite from database.
    fun unFavoriteProduct(context: Context, pushKey : String){
        CoroutineScope(Dispatchers.IO).launch {
            val database = DatabaseModule.provideDatabase(context)
            CoroutineScope(Dispatchers.Main).launch {
                database.bridalDao().deleteProduct(pushKey)
            }
        }
    }

    // fun check select of favorite button.
    fun checkSelect(context: Context, pushKey : String, toggleButton: ToggleButton){
        CoroutineScope(Dispatchers.IO).launch{
            val database = DatabaseModule.provideDatabase(context)
            CoroutineScope(Dispatchers.Main).launch{
                val result = database.bridalDao().readAll()
                for (product in result){
                    if( product.pushKey == pushKey){
                        toggleButton.isChecked = true
                    }
                }
            }
        }
    }
}
