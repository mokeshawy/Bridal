package com.example.bridal.firestore
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.bridal.model.user
import com.example.bridal.ui.activits.settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


//class firestoreclass {
//    private val mfirestore = FirebaseFirestore.getInstance()
//    fun  registeruser(activity: register, userinfo:user){
//        mfirestore.collection(constants.users).document(userinfo.id).set(userinfo, SetOptions.merge()).addOnSuccessListener {
//            activity.useregistersucces()
//        }.addOnFailureListener {
//            activity.hidepprogreedialog()
//            Log.e(
//                    activity.javaClass.simpleName,"err",
//
//            )
//
//        }
//    }
//    fun  getcurrentuserid() : String{
//        val  currentUser = FirebaseAuth.getInstance().currentUser
//        var  currentuid=""
//        if (currentuid != null){
//            currentuid = currentUser.uid
//        }
//        return currentuid
//    }
//    fun getUserDetails(activity: Activity) {
//
//        // Here we pass the collection name from which we wants the data.
//        mfirestore.collection(constants.users)
//                // The document id to get the Fields of user.
//                .document(getcurrentuserid()).get()
//
//                .addOnSuccessListener { document ->
//
//                    Log.i(activity.javaClass.simpleName, document.toString())
//
//                    // Here we have received the document snapshot which is converted into the User Data model object.
//                    val user = document.toObject(user::class.java)!!
//                     val sharedPreferences = activity.getSharedPreferences(
//                         constants.bridalpreference,
//                         Context.MODE_PRIVATE
//                     )
//                    val editor = sharedPreferences.edit()
//                    editor.putString(
//                        constants.logged_username,
//                        "${user.firstname}  ${user.lastname}"
//                    )
//                    editor.apply()
//
//                    // TODO Step 6: Pass the result to the Login Activity.
//                    // START
//                    when (activity) {
//                        is com.example.bridal.ui.activits.reallogin -> {
//                            // Call a function of base activity for transferring the result to it.
//                            activity.userLoggedInSuccess(user)
//                            //val intent1 = Intent(activity,dashboard::class.java)
//                           // activity.startActivity(intent1)
//                            //activity.finish()
//
//
//                             //  l = true
//                                // val sharedPreference = activity.getSharedPreferences(constants.wo,Context.MODE_PRIVATE)
//                                ///   val edito = sharedPreference.edit()
//
//                                //  edito.putBoolean(
//                                  //  constants.wo, l)
//                            //edito.apply()
//                           /// Handler().postDelayed({ l = false
//                                ////    },1000*60*2)
//
//                        }is settings ->{
//                        // Call a function of base activity for transferring the result to it.
//                           //activity.userDetailsuc(user)
//                    }
//                    }
//
//                    }
//                    // END
//
//                .addOnFailureListener { e ->
//                    // Hide the progress dialog if there is any error. And print the error in log.
//                    when (activity) {
//                        is reallogin1 -> {
//                            activity.hidepprogreedialog()
//                           // l = false
//                            //val sharedPreference = activity.getSharedPreferences(constants.wo, Context.MODE_PRIVATE)
//                            //val edito = sharedPreference.edit()
//                            //edito.putBoolean(
//                              //      constants.wo, l)
//                            //edito.apply()
//                        } is settings ->{
//                            activity.hidepprogreedialog()
//                        }
//                    }
//                }
//    }
//    fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?) {
//
//        //getting the storage reference
//        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
//            constants.USER_PROFILE_IMAGE + System.currentTimeMillis() + "."
//                    + constants.getFileExtension(
//                activity,
//                imageFileURI
//            )
//        )
//
//        //adding the file to reference
//        sRef.putFile(imageFileURI!!)
//            .addOnSuccessListener { taskSnapshot ->
//                // The image upload is success
//                Log.e(
//                    "Firebase Image URL",
//                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
//                )
//
//                // Get the downloadable url from the task snapshot
//                taskSnapshot.metadata!!.reference!!.downloadUrl
//                    .addOnSuccessListener { uri ->
//                        Log.e("Downloadable Image URL", uri.toString())
//
//                        // Here call a function of base activity for transferring the result to it.
//                        when (activity) {
//                            is register -> {
//                            }
//                        }
//                    }
//            }
//            .addOnFailureListener { exception ->
//
//                // Hide the progress dialog if there is any error. And print the error in log.
//                when (activity) {
//                    is register -> {
//                    }
//                }
//
//                Log.e(
//                    activity.javaClass.simpleName,
//                    exception.message,
//                    exception
//                )
//            }
//    }
//}


    // START
    // A function to upload the image to the cloud storage.


    // END

