package com.example.bridal.ui.myaddsfragment

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyAddsViewModel : ViewModel() {

    var userProductLiveData = MutableLiveData<ArrayList<ProductModel>>()
    var userProductArray    = ArrayList<ProductModel>()
    var firebaseDatabase    = FirebaseDatabase.getInstance()
    var productReference    = firebaseDatabase.getReference(Constants.PRODUCT_REFERENCE)

    // fun show data for product by id for user login.
    fun showProductForUser(context: Context , progressBar: ProgressBar , tv_product_not_found : TextView){
        userProductArray = ArrayList()
        productReference.orderByChild(Constants.getCurrentUser()).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userProductArray.clear()
               for(ds in snapshot.children){
                   progressBar.visibility = View.VISIBLE

                   val product = ds.getValue(ProductModel::class.java)!!
                   product.productId = ds.key.toString()
                   userProductArray.add(product)
               }
                userProductLiveData.value = userProductArray

                if(userProductArray.size > 0){
                    tv_product_not_found.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }else{
                    tv_product_not_found.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deleteProduct( productId : String){
        productReference.child(productId).removeValue()
    }
}