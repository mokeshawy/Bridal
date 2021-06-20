package com.example.bridal.ui.addproductfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants
import com.google.firebase.database.FirebaseDatabase


class AddProductViewModel : ViewModel(){

    var etProductTitle          = MutableLiveData<String>("")
    var etProductPrice          = MutableLiveData<String>("")
    var etProductDescription    = MutableLiveData<String>("")


    var firebaseDatabase = FirebaseDatabase.getInstance()
    var productReference = firebaseDatabase.getReference(Constants.PRODUCT_REFERENCE)

    fun addProductItem( categoryName : String){

        var product = ProductModel(Constants.getCurrentUser(),
            categoryName,
            etProductTitle.value!!,
            etProductPrice.value!!,
            "",
            etProductDescription.value!!,
            "","")
        productReference.push().setValue(product)
    }
}