package com.example.bridal.ui.completeprofilefragment

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.bridal.R
import com.example.bridal.util.Constants
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CompleteProfileViewModel : ViewModel() {


    var etFirstName     = MutableLiveData<String>("")
    var etLastName      = MutableLiveData<String>("")
    var etEmail         = MutableLiveData<String>("")
    var etMobileNumber  = MutableLiveData<String>("")

    var firebaseDatabase    = FirebaseDatabase.getInstance()
    var userReference       = firebaseDatabase.getReference(Constants.USERS)
    var profileStorageRef    = FirebaseStorage.getInstance().reference

    fun showDefaultData( context : Context){
        val myPreference = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
        etFirstName.value   = myPreference.getString(Constants.FIRST_NAME_KEY,"")
        etLastName.value    = myPreference.getString(Constants.LAST_NAME_KEY,"")
        etEmail.value       = myPreference.getString(Constants.USER_EMAIL_KEY,"")
    }

    fun completeAndEditProfile(context: Context,
                               view : View,
                               progressBar: ProgressBar,
                               profileUri : Uri,
                               countryName : String ,
                               radioButton : RadioButton){
        val myPreference = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
        val completeProfile = myPreference!!.getInt(Constants.USER_COMPLETE_PROFILE,0)
        progressBar.visibility = View.VISIBLE
        val map = HashMap<String , Any>()

        val gender = if(radioButton.isChecked){
            Constants.GENDER_MALE
        }else{
            Constants.GENDER_FEMALE
        }
        if(etFirstName.value!!.isNotEmpty()){
            map[Constants.FIRST_NAME_KEY] = etFirstName.value!!
        }
        if(etLastName.value!!.isNotEmpty()){
            map[Constants.LAST_NAME_KEY] = etLastName.value!!
        }
        if(etMobileNumber.value!!.isNotEmpty()){
            map[Constants.USER_MOBILE_KEY] = etMobileNumber.value!!
        }
        if(gender.isNotEmpty()){
            map[Constants.USER_GENDER_KEY] = gender
        }
        if(completeProfile == 1){
            map[Constants.USER_IMAGE_KEY] = myPreference!!.getString(Constants.USER_PROFILE_IMAGE,"").toString()
        }else{
            //when user no select new image will save default image.
            map[Constants.USER_IMAGE_KEY] = Constants.SOURCE_IMAGE_PROFILE
        }

        map[Constants.USER_COUNTRY_KEY]         = countryName
        map[Constants.USER_COMPLETE_PROFILE]    = 1


        userReference.child(Constants.getCurrentUser()).updateChildren(map)
        progressBar.visibility = View.GONE

        Navigation.findNavController(view).navigate(R.id.action_completeProfileFragment_to_loginFragment)
        val profileStorage : StorageReference = profileStorageRef.child("Photo/"+Constants.USER_COMPLETE_PROFILE_IMAGE+System.currentTimeMillis())
        profileStorage.putFile(profileUri).addOnCompleteListener { imageUpload ->
            progressBar.visibility = View.VISIBLE
            if(imageUpload.isSuccessful){
                profileStorage.downloadUrl.addOnSuccessListener { imageUrl ->
                    map[Constants.USER_IMAGE_KEY] = imageUrl.toString()
                    userReference.child(Constants.getCurrentUser()).updateChildren(map)
                    progressBar.visibility = View.GONE
                }
            }
        }

    }
}