package com.example.bridal.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import com.example.bridal.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_progress.*

object  Constants {

    // for "users" reference
    const val USERS : String = "users"

    // Child for user Reference
    const val USER_ID           : String = "userId"
    const val FIRST_NAME_KEY    : String  = "firstName"
    const val LAST_NAME_KEY     : String  = "lastName"
    const val USER_EMAIL_KEY    : String  = "email"
    const val USER_IMAGE_KEY    : String  = "image"
    const val USER_MOBILE_KEY   : String  = "mobile"
    const val USER_GENDER_KEY   : String  = "gender"
    const val USER_COMPLETE_PROFILE : String = "profileCompleted"

    // bundle key
    const val USERS_SHARED_KEY = "userKey"

    const  val users:String ="users"
    const val bridalpreference = "bridalpref"
    const val logged_username = "logged_in_username"
    const val extrauserdetail = "ma3lomat"
    const val wo = "wo"
    const val USER_PROFILE_IMAGE:String = "User_Profile_Image"
    const val PICK_IMAGE_REQUEST_CODE = 2
    const val EXTRA_USER_DETAILS: String = "extra_user_details"



    // fun snack bar show error and successful
    fun showErrorSnackBar(message : String, errorMessage : Boolean, context: Context, view : View){

        val snackBar = Snackbar.make( view , message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if(errorMessage){
            snackBarView.setBackgroundColor(ContextCompat.getColor( context , R.color.err))
        }else{
            snackBarView.setBackgroundColor(ContextCompat.getColor(context , R.color.page))
        }
        snackBar.show()
    }

    // fun hide progress bar
    private lateinit var mProgressDialog : Dialog
    fun showProgressDialog(text:String , context: Context){
        mProgressDialog = Dialog(context)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.textView5.text = text
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }

    // fun hide progress bar
    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }


    // getUserID function
    fun getCurrentUser() : String{
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is nut null or else it will be blank
        var currentUserID = ""
        if(currentUserID != null){

            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    /**
    * A function for user profile image selection from phone storage.
    */
   fun showImageChooser(activity: Activity) {
     // An intent for launching the image selection of phone storage.
     val galleryIntent = Intent(
       Intent.ACTION_PICK,
       MediaStore.Images.Media.EXTERNAL_CONTENT_URI
     )
     // Launches the image selection of phone storage using the constant code.
     activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
   }

   /**
    * A function to get the image file extension of the selected image.
    *
    * @param activity Activity reference.
    * @param uri Image file uri.
    */
   fun getFileExtension(activity: Activity, uri: Uri?): String? {
     /*
      * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
      *
      * getSingleton(): Get the singleton instance of MimeTypeMap.
      *
      * getExtensionFromMimeType: Return the registered extension for the given MIME type.
      *
      * contentResolver.getType: Return the MIME type of the given content URL.
      */
     return MimeTypeMap.getSingleton()
       .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
   }
 }
