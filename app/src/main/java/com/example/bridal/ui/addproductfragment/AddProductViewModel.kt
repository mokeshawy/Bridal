package com.example.bridal.ui.addproductfragment

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.R
import com.example.bridal.model.ProductModel
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
    fun addProductItem(context          : Context,
                       view             : View,
                       categoryName     : String,
                       imageUriOne      : Uri,
                       imageUriTow      : Uri,
                       imageUriThree    : Uri,
                       videoUri         : Uri,
                       progressBar      : ProgressBar){


        if(etProductTitle.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_title)), true , context , view)
        }else if(etProductPrice.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_price)), true , context , view)
        }else if(etProductDescription.value!!.trim().isEmpty()){
            Constants.showErrorSnackBar(context.resources.getString((R.string.err_validate_product_description)), true , context , view)
        }else{
            val myPreference = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
            val userName     =      "${myPreference!!.getString(Constants.FIRST_NAME_KEY,"")}"+
                                    "${myPreference!!.getString(Constants.LAST_NAME_KEY,"")}"
            progressBar.visibility = View.VISIBLE
            val pushKey = productReference.push().key
            val map = HashMap<String , Any>()

            map[Constants.PRODUCT_USER_ID]          = Constants.getCurrentUser()
            map[Constants.PRODUCT_USER_NAME]        = userName
            map[Constants.PRODUCT_CATEGORY_NAME]    = categoryName
            map[Constants.PRODUCT_TITLE]            = etProductTitle.value!!
            map[Constants.PRODUCT_PRICE]            = etProductPrice.value!!
            map[Constants.PRODUCT_IMAGE_ONE]        = Constants.SOURCE_IMAGE_ONE
            map[Constants.PRODUCT_IMAGE_TOW]        = Constants.SOURCE_IMAGE_TOW
            map[Constants.PRODUCT_IMAGE_THREE]      = Constants.SOURCE_IMAGE_THREE
            map[Constants.PRODUCT_DESCRIPTION]      = etProductDescription.value!!
            map[Constants.PRODUCT_LATITUDE]         = ""
            map[Constants.PRODUCT_LONGITUDE]        = ""
            map[Constants.PRODUCT_PUSH_KEY]         = pushKey.toString()
            map[Constants.PRODUCT_RATE]             = 1
            productReference.child(pushKey.toString()).setValue(map)

            // upload image num one.
            val storageOne : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageOne.putFile(imageUriOne).addOnCompleteListener { imageOne ->
                if(imageOne.isSuccessful){
                    storageOne.downloadUrl.addOnSuccessListener { imageUrlOne ->
                        map[Constants.PRODUCT_IMAGE_ONE]    = imageUrlOne.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                        progressBar.visibility = View.GONE
                    }
                }
            }
            // upload image num tow.
            val storageTow : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageTow.putFile(imageUriTow).addOnCompleteListener { imageTow ->
                if(imageTow.isSuccessful){
                    storageTow.downloadUrl.addOnSuccessListener { imageUrlTow ->
                        map[Constants.PRODUCT_IMAGE_TOW]    = imageUrlTow.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                        progressBar.visibility = View.GONE
                    }
                }
            }
            // upload imge num three.
            val storageThree : StorageReference = storageRef.child("Photo/"+System.currentTimeMillis()+"product_image.jpg")
            storageThree.putFile(imageUriThree).addOnCompleteListener { imageThree ->
                if(imageThree.isSuccessful){
                    storageThree.downloadUrl.addOnSuccessListener { imageUrlThree ->
                        map[Constants.PRODUCT_IMAGE_THREE]  = imageUrlThree.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                        progressBar.visibility = View.GONE
                    }
                }
            }
            // upload video.
            val videoStorage : StorageReference = storageRef.child("Video/"+System.currentTimeMillis())
            videoStorage.putFile(videoUri).addOnCompleteListener { video ->
                if(video.isSuccessful){
                    videoStorage.downloadUrl.addOnSuccessListener { videoUrl ->
                        map[Constants.PRODUCT_VIDEO] = videoUrl.toString()
                        productReference.child(pushKey.toString()).updateChildren(map)
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    // fun show select more image. when go user update profile to premium option will go unlock select more image.
    fun addShowMoreImageAndVideo(context : Context
                         ,ll_user_premium_require : LinearLayout,
                         ll_user_premium_done : LinearLayout ){

        val myPreference    = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
        val userPremium     = myPreference.getInt(Constants.USER_PREMIUM_COMPLETE,0)

        if(userPremium == 0){
            ll_user_premium_require.visibility  = View.VISIBLE
            ll_user_premium_done.visibility     = View.GONE
        }else{
            ll_user_premium_done.visibility     = View.VISIBLE
            ll_user_premium_require.visibility  = View.GONE
        }
    }
}