package com.example.bridal.ui.loginfragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.bridal.R
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class LoginViewModel : ViewModel() {


    var etUserEmail     = MutableLiveData<String>("")
    var etUserPassword  = MutableLiveData<String>("")

    // firebase instance.
    private val firebaseAuth        = FirebaseAuth.getInstance()
    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val userReference       = firebaseDatabase.getReference(Constants.USERS)

    // fun change language.
     fun showChangeLang(context: Context , loginFragment: LoginFragment) {
        val listItems = arrayOf("عربي", "English")
        val mBuilder = AlertDialog.Builder(context)
        mBuilder.setTitle("choose language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                setLocate("ar",context)
                loginFragment.activity?.recreate()
            } else if (which == 1) {
                setLocate("en" , context)
                loginFragment.activity?.recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }


    @Suppress("DEPRECATION")
    private fun setLocate(Lang: String , context: Context) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }


    fun loadLocate(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language!! , context)
    }

    // Validate input for login user
    private fun validateLoginDetails( context: Context , view : View): Boolean {
        return when {
            TextUtils.isEmpty(etUserEmail.value!!.trim { it <= ' ' }) -> {
                Constants.showErrorSnackBar(context.resources.getString((R.string.err_msg_erremail)), false , context , view)
                false
            }
            TextUtils.isEmpty(etUserPassword.value!!.trim { it <= ' ' }) -> {
                Constants.showErrorSnackBar(context.resources.getString((R.string.err_msg_pass1)), false , context, view)
                false
            }
            etUserPassword.value!!.length < 6 -> {
                Constants.showErrorSnackBar(context.resources.getString((R.string.err_msg_length_Password)), false , context, view)
                false
            }
            else -> {
                true
            }
        }
    }

    // fun user login.
    fun userLogin( context: Context , view: View , progressBar : ProgressBar){
        // check validate input.
        if(validateLoginDetails(context , view)){
            progressBar.visibility = View.VISIBLE
            firebaseAuth.signInWithEmailAndPassword( etUserEmail.value!! , etUserPassword.value!!).addOnCompleteListener {
                if(it.isSuccessful){
                    if(firebaseAuth.currentUser?.isEmailVerified!!){
                        // get data for user login from database.
                        userReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener( object : ValueEventListener{
                            @SuppressLint("CommitPrefEdits")
                            override fun onDataChange(snapshot: DataSnapshot) {

                                val user = snapshot.getValue(UserModel::class.java)!!
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)

                                // share the object for user login by sharedPreference.
                                val myPreference = context.getSharedPreferences(Constants.USERS_SHARED_KEY,Context.MODE_PRIVATE)
                                val editor =myPreference!!.edit()
                                editor.putString(Constants.FIRST_NAME_KEY,user.firstName)
                                editor.putString(Constants.LAST_NAME_KEY,user.lastName)
                                editor.putString(Constants.USER_EMAIL_KEY,user.email)
                                editor.putString(Constants.USER_PROFILE_IMAGE,user.image)
                                editor.putString(Constants.USER_MOBILE_KEY,user.mobile.toString())
                                editor.putString(Constants.USER_GENDER_KEY,user.gender)
                                editor.apply()
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
                                progressBar.visibility = View.GONE
                            }
                        })
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }
}