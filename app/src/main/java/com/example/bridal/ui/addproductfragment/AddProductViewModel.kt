package com.example.bridal.ui.addproductfragment

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.R
import com.example.bridal.model.ProductModel
import com.example.bridal.util.Constants
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class AddProductViewModel : ViewModel(){

    var etProductTitle          = MutableLiveData<String>("")
    var etProductPrice          = MutableLiveData<String>("")
    var etProductDescription    = MutableLiveData<String>("")


    var firebaseDatabase = FirebaseDatabase.getInstance()
    var productReference = firebaseDatabase.getReference(Constants.PRODUCT_REFERENCE)
    var storageRef       = FirebaseStorage.getInstance().reference

    // fun add new product.
    fun addProductItem(context : Context, view : View, categoryName : String , imageUriOne : Uri , imageUriTow : Uri,imageUriThree : Uri){
        if(etProductTitle.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_title)), true , context , view)
        }else if(etProductPrice.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_price)), true , context , view)
        }else if(etProductDescription.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_description)), true , context , view)
        }else{

            var pushKey = productReference.push().key
            val map = HashMap<String , Any>()

            map[Constants.PRODUCT_USER_ID]          = Constants.getCurrentUser()
            map[Constants.PRODUCT_CATEGORY_NAME]    = categoryName
            map[Constants.PRODUCT_TITLE]            = etProductTitle.value!!
            map[Constants.PRODUCT_PRICE]            = etProductPrice.value!!

            val storageOne : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageOne.putFile(imageUriOne).addOnCompleteListener { imageOne ->
                if(imageOne.isSuccessful){
                    storageOne.downloadUrl.addOnSuccessListener { imageUrlOne ->
                        map[Constants.PRODUCT_IMAGE_ONE]    = imageUrlOne.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                    }
                }
            }
            val storageTow : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageTow.putFile(imageUriTow).addOnCompleteListener { imageTow ->
                if(imageTow.isSuccessful){
                    storageTow.downloadUrl.addOnSuccessListener { imageUrlTow ->
                        if(imageTow.isSuccessful){
                            map[Constants.PRODUCT_IMAGE_TOW]    = imageUrlTow.toString()
                            productReference.child(pushKey.toString()).updateChildren(map)
                        }else{
                            map[Constants.PRODUCT_IMAGE_TOW]    = Constants.SOURCE_IMAGE_TOW
                            productReference.child(pushKey.toString()).updateChildren(map)
                        }

                    }
                }
            }
            val storageThree : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageThree.putFile(imageUriThree).addOnCompleteListener { imageThree ->
                if(imageThree.isSuccessful){
                    storageThree.downloadUrl.addOnSuccessListener { imageUrlThree ->
                        map[Constants.PRODUCT_IMAGE_THREE]  = imageUrlThree.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                    }
                }
            }
            map[Constants.PRODUCT_VIDEO]            = ""
            map[Constants.PRODUCT_DESCRIPTION]      = etProductDescription.value!!
            map[Constants.PRODUCT_LATITUDE]         = ""
            map[Constants.PRODUCT_LONGITUDE]        = ""
            productReference.child(pushKey.toString()).setValue(map)
        }
    }
}