package com.example.bridal.ui.activits

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.bridal.R
import com.google.firebase.auth.FirebaseAuth

class settings : Baseactivity(),View.OnClickListener{

    //private lateinit var mUserDetails: user



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Add a global variable for URI of a selected image from phone storage.
        //tv_edit.setOnClickListener(this)



    }

//    private fun getuserdetails() {
//        shwprogressdialog(resources.getString(R.string.plswt))
//        firestoreclass().getUserDetails(this)
//    }
//
//    fun userDetailsuc(user1: user) {
//         mUserDetails = user1
//
//        hidepprogreedialog()
//        //glideLoader(this).loadUserPicture(user1.image, iv_user_photo)
//
//        tv_name.text = "${user1.firstname} ${user1.lastname}"
//        tv_email.text = user1.email
//
//
//    }

    override fun onResume() {
        super.onResume()
        //getuserdetails()
        //hidepprogreedialog()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.tv_edit -> {
                    val intent = Intent(this, MainActivity::class.java)
                    //intent.putExtra(constants.EXTRA_USER_DETAILS, mUserDetails)
                    startActivity(intent)
                }

                R.id.btn_logout -> {

                    FirebaseAuth.getInstance().signOut()

                    val intent = Intent(this, reallogin::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}






