package com.example.bridal.ui.productfragment

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.model.ProductModel
import com.example.bridal.roomdb.DatabaseModule
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    var productListLiveData = MutableLiveData<ArrayList<ProductModel>>()
    var productArrayList    = ArrayList<ProductModel>()

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var productReference = firebaseDatabase.getReference(Constants.PRODUCT_REFERENCE)
    // fun read data for product from database.
    fun readProduct(position : String , tv_not_found : TextView , progressBar: ProgressBar){

        productArrayList = ArrayList()

        productReference.orderByChild(position).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    val product = ds.getValue(ProductModel::class.java)!!
                    if(product.categoryPosition == position){
                        productArrayList.add(product)
                    }
                }
                productListLiveData.value = productArrayList
                if(productArrayList.size > 0 ){
                    tv_not_found.visibility = View.GONE
                    progressBar.visibility  = View.GONE
                }else{
                    tv_not_found.visibility = View.VISIBLE
                    progressBar.visibility  = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    // add product to room data base
    fun addProductToFavorite(context: Context , productModel: ProductModel){
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
    fun unFavoriteProduct(context: Context , pushKey : String){
        CoroutineScope(Dispatchers.IO).launch {
            val database = DatabaseModule.provideDatabase(context)
            CoroutineScope(Dispatchers.Main).launch {
                database.bridalDao().deleteProduct(pushKey)
            }
        }
    }

    // fun check select of favorite button.
    fun checkSelect(context: Context , pushKey : String , toggleButton: ToggleButton){
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