package com.example.bridal.ui.productfragment

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductViewModel : ViewModel() {

    var productListLiveData = MutableLiveData<ArrayList<ProductModel>>()
    var productArrayList    = ArrayList<ProductModel>()

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var productReference = firebaseDatabase.getReference(Constants.PRODUCT_REFERENCE)
    // fun read data for product from database.
    fun readProduct(categoryName : String , tv_not_found : TextView , progressBar: ProgressBar){

        productArrayList = ArrayList()

        productReference.orderByChild(categoryName).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    var product = ds.getValue(ProductModel::class.java)!!
                    if(product.categoryName == categoryName){
                        productArrayList.add(product)
                    }
                }
                productListLiveData.value = productArrayList
                if(productArrayList.size > 0 ){
                    tv_not_found.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }else{
                    tv_not_found.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}