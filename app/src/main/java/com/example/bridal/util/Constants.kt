package com.example.bridal.util


import android.app.Dialog
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.bridal.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


object  Constants {

    // for "users" reference
    const val USERS : String = "users"

    // Child for user Reference
    const val USER_ID           : String    = "userId"
    const val FIRST_NAME_KEY    : String    = "firstName"
    const val LAST_NAME_KEY     : String    = "lastName"
    const val USER_EMAIL_KEY    : String    = "email"
    const val USER_IMAGE_KEY    : String    = "image"
    const val USER_MOBILE_KEY   : String    = "mobile"
    const val USER_COUNTRY_KEY  : String    = "country"
    const val USER_GENDER_KEY   : String    = "gender"
    const val USER_COMPLETE_PROFILE : String = "profileCompleted"
    const val USER_PREMIUM_COMPLETE  = "userPremium"


    // shared preference key
    const val USERS_SHARED_KEY = "userKey"

    // bundle key
    const val PRODUCT_ITEM_KEY = "productKey"

    // extra bundle key
    const val EXTRA_PRODUCT_ITEM_KEY = "extraProductKey"

    // bundle key for image.
    const val IMAGE_FROM_MY_ADD_DETAILS = "imageMyAddDetails"
    const val IMAGE_FROM_HOME_DETAILS   = "imageHomeDetails"

    // bundle for send UserObject
    const val PRODUCT_CHAT_OBJECT    = "productObject"

    // bundle user key.
    const val USER_MODEL_KEY = "usersBundle"
    // product reference
    const val PRODUCT_REFERENCE = "product"

    // child of product.
    const val PRODUCT_USER_ID       = "userId"
    const val PRODUCT_USER_NAME     = "userName"
    const val PRODUCT_CATEGORY_POSITION = "categoryPosition"
    const val PRODUCT_CATEGORY_NAME = "categoryName"
    const val PRODUCT_TITLE         = "productTitle"
    const val PRODUCT_PRICE         = "productPrice"
    const val PRODUCT_IMAGE_ONE     = "productImageOne"
    const val PRODUCT_IMAGE_TOW     = "productImageTow"
    const val PRODUCT_IMAGE_THREE   = "productImageThree"
    const val PRODUCT_IMAGE_FOUR    = "productImageFour"
    const val PRODUCT_IMAGE_FIVE    = "productImageFive"
    const val PRODUCT_IMAGE_SIX     = "productImageSix"
    const val PRODUCT_VIDEO         = "productVideo"
    const val PRODUCT_VIDEO_TWO     = "productVideoTwo"
    const val PRODUCT_VIDEO_THREE   = "productVideoThree"
    const val PRODUCT_DESCRIPTION   = "productDescription"
    const val PRODUCT_LATITUDE      = "latitude"
    const val PRODUCT_LONGITUDE     = "longitude"
    const val PRODUCT_PUSH_KEY      = "pushKey"
    const val PRODUCT_RATE          = "rate"


    // Chat Reference
    const val CHAT_REFERENCE = "Chat"

    // Child chat reference
    const val CHAT_SENDER_ID        = "senderId"
    const val CHAT_RECEIVER_ID      = "receiverId"
    const val CHAT_MESSAGE          = "message"
    const val CHAT_PUSH_KEY         = "chatKey"

    // chat list reference
    const val CHAT_LIST_REFERENCE   = "ChatList"

    // Child ChatList reference
    const val CHAT_LIST_ID = "id"


    // request number for image.
    const val IMAGE_NUM_ONE_KEY     = 111
    const val IMAGE_NUM_TOW_KEY     = 222
    const val IMAGE_NUM_THREE_KEY   = 333
    const val IMAGE_NUM_FOUR_KEY    = 444
    const val IMAGE_NUM_FIVE_KEY    = 555
    const val IMAGE_NUM_SIX_KEY     = 666

    // request number key.
    const val VIDEO_KEY             = 777
    const val VIDEO_TWO_KEY         = 888
    const val VIDEO_THREE_KEY       = 999


    // const image in firebase.
    const val SOURCE_IMAGE_ONE      = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_one.png?alt=media&token=ad4aa617-3021-4c6d-9344-7c7bd3f1e6ac"
    const val SOURCE_IMAGE_TOW      = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_three.png?alt=media&token=fd9132db-656a-44de-aa05-91fa196b157e"
    const val SOURCE_IMAGE_THREE    = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_tow.png?alt=media&token=9bf21e65-a19c-42be-9cc1-f6ad694875db"
    const val SOURCE_IMAGE_FOUR     = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_four.png?alt=media&token=e302610f-9f11-4e5f-b2eb-6eefdaab72fe"
    const val SOURCE_IMAGE_FIVE     = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_five.png?alt=media&token=447ee289-3df5-4fa3-9689-7bf958013c7f"
    const val SOURCE_IMAGE_SIX      = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fimage_six.png?alt=media&token=90db76a7-829f-4f5d-bad8-77bdbc601689"
    const val SOURCE_IMAGE_PROFILE  = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Photo%2Fdefualt_image_profile.png?alt=media&token=a7e4096c-775a-456d-9e54-2ab470b7cac0"
    const val SOURCE_VIDEO          = "https://firebasestorage.googleapis.com/v0/b/bridal-f3ec8.appspot.com/o/Video%2Fdefault_bridal_video.mp4?alt=media&token=1dd4d860-65d3-4d7b-8ccc-034e58d2fcae"

    // user complete profile.
    const val USER_COMPLETE_PROFILE_IMAGE = "profile_image"
    const val IMAGE_PROFILE_KEY   = 555

    // gender.
    const val GENDER_MALE   = "male"
    const val GENDER_FEMALE = "female"


    // Room Database table name
    const val DATA_BASE_NAME = "Bridal"

    // rating bar kay.
    const val RATING_KEY = "rating"


    const val BASE_URL = "https://fcm.googleapis.com"
    const val SERVER_KEY = "AAAAK-YrOaw:APA91bGFxpYR6PfoxSR5Govy6E7XV6XtUd1UU75zI0h1EfUcimjFnN7hKITNLY40-ttM-YYlPJqTJUMrNSCgeJpe1mui2mhPtiM3x4DGvbX3cBxtKbLfFDCy8rJFXcORZnZC54P23d4a"
    const val CONTENT_TYPE = "application/json"
    const val TOPIC = "/topics/myTopic2"

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
//    fun showProgressDialog(text:String , context: Context){
//        mProgressDialog = Dialog(context)
//        mProgressDialog.setContentView(R.layout.dialog_progress)
//        mProgressDialog.textView5.text = text
//        mProgressDialog.setCancelable(false)
//        mProgressDialog.setCanceledOnTouchOutside(false)
//        mProgressDialog.show()
//    }

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



    const  val users:String ="users"
    const val bridalpreference = "bridalpref"
    const val logged_username = "logged_in_username"
    const val extrauserdetail = "ma3lomat"
    const val wo = "wo"
    const val USER_PROFILE_IMAGE:String = "User_Profile_Image"
    const val PICK_IMAGE_REQUEST_CODE = 2
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
//    /**
//    * A function for user profile image selection from phone storage.
//    */
//   fun showImageChooser(activity: Activity) {
//     // An intent for launching the image selection of phone storage.
//     val galleryIntent = Intent(
//       Intent.ACTION_PICK,
//       MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//     )
//     // Launches the image selection of phone storage using the constant code.
//     activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
//   }
//
//   /**
//    * A function to get the image file extension of the selected image.
//    *
//    * @param activity Activity reference.
//    * @param uri Image file uri.
//    */
//   fun getFileExtension(activity: Activity, uri: Uri?): String? {
//     /*
//      * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
//      *
//      * getSingleton(): Get the singleton instance of MimeTypeMap.
//      *
//      * getExtensionFromMimeType: Return the registered extension for the given MIME type.
//      *
//      * contentResolver.getType: Return the MIME type of the given content URL.
//      */
//     return MimeTypeMap.getSingleton()
//       .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
//   }
 }
