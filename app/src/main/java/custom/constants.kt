package custom

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object  constants {

    const  val users:String ="users"
    const val bridalpreference = "bridalpref"
    const val logged_username = "logged_in_username"
    const val extrauserdetail = "ma3lomat"
    const val wo = "wo"
    const val USER_PROFILE_IMAGE:String = "User_Profile_Image"
    const val PICK_IMAGE_REQUEST_CODE = 2
    const val EXTRA_USER_DETAILS: String = "extra_user_details"



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
