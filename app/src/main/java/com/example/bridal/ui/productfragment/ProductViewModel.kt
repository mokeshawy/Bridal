package com.example.bridal.ui.productfragment

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
    fun readProduct(categoryName : String){

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
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}