package com.example.bridal.ui.myaddsfragment

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.R
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
        productReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userProductArray.clear()
               for(ds in snapshot.children){
                   progressBar.visibility = View.VISIBLE

                   val product = ds.getValue(ProductModel::class.java)!!

                   if(Constants.getCurrentUser() == product.userId){
                       userProductArray.add(product)
                   }
               }
                userProductLiveData.value = userProductArray

                if(userProductArray.size > 0){
                    tv_product_not_found.visibility = View.GONE
                    progressBar.visibility          = View.GONE
                }else{
                    tv_product_not_found.visibility = View.VISIBLE
                    progressBar.visibility          = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    // fun for delete product.
    fun deleteProduct( context: Context , pushKey : String){
        val alert = AlertDialog.Builder(context)
        alert.setTitle(context.resources.getString(R.string.title_delete))
        alert.setMessage(context.resources.getString(R.string.title_message_for_delete))
        alert.setPositiveButton(context.getString(R.string.title_dialog_yes)){dialog,which ->
            productReference.child(pushKey).removeValue()
        }
        alert.setNegativeButton(context.resources.getString(R.string.title_dialog_no),null)
        alert.create().show()
    }
}