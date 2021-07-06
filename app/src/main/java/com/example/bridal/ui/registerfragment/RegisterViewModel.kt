package com.example.bridal.ui.registerfragment

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.bridal.R
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel : ViewModel() {



    var etFirstName         = MutableLiveData<String>("")
    var etLastName          = MutableLiveData<String>("")
    var etEmail             = MutableLiveData<String>("")
    var etPassword          = MutableLiveData<String>("")
    var etConfirmPassword   = MutableLiveData<String>("")


    // fun validate data entry from user register
    private fun validateInput(context: Context, view : View) : Boolean {

        return when {
            TextUtils.isEmpty(etFirstName.value.toString().trim { it <=' ' }) ->{
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_firstname),true , context, view )
                false
            }

            TextUtils.isEmpty(etLastName.value.toString().trim { it <=' ' }) ->{
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_lastname),true , context, view )
                false
            }

            TextUtils.isEmpty(etEmail.value.toString().trim { it <=' ' }) ->{
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_erremail),true , context, view )
                false
            }

            TextUtils.isEmpty(etPassword.value.toString().trim { it <=' ' }) ->{
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_pass1),true , context, view )
                false
            }
            etPassword.value.toString().length < 6 ->{

                Constants.showErrorSnackBar(context.getString(R.string.err_msg_length_Password),true , context, view )
                false
            }
            TextUtils.isEmpty(etConfirmPassword.value.toString().trim { it <=' ' }) ->{
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_confirmpass),true , context, view )
                false
            }

            etPassword.value.toString().trim { it <=' ' } != etConfirmPassword.value.toString().trim { it <=' ' } -> {
                Constants.showErrorSnackBar(context.getString(R.string.err_msg_notmatched), true , context, view )
                false
            }
            else -> {
                true
            }
        }
    }


    // Connect whit authentication firebase
    var firebaseAuth        = FirebaseAuth.getInstance()
    var firebaseDatabase    = FirebaseDatabase.getInstance()
    var userReference       = firebaseDatabase.getReference(Constants.USERS)

    // fun register user
    fun registerUser(context: Context , view : View , progressBar: ProgressBar){

        // check validate function if the entries are valid or no
        if(validateInput(context , view )){

            progressBar.visibility = View.VISIBLE

            // Get the text from editText and trim space
            val email       = etEmail.value.toString().trim { it <= ' ' }
            val password    = etPassword.value.toString().trim { it <= ' ' }

            // Create a new account by firebase authentication
            firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener{ task ->

                if(task.isSuccessful){
                    // Send email verification
                    firebaseAuth.currentUser?.sendEmailVerification()

                    val userInfo = firebaseAuth.currentUser?.uid

                    val user = UserModel(
                        userInfo.toString(),
                        etFirstName.value.toString().trim { it <= ' ' },
                        etLastName.value.toString().trim { it <= ' ' },
                        etEmail.value.toString().trim { it <= ' ' }
                    )

                    // Insert value from userModel to real time data base
                    userReference.child(Constants.getCurrentUser()).setValue(user)

                    progressBar.visibility = View.GONE
                    // Show snack bar for register success
                    Constants.showErrorSnackBar("Register Done",false,context,view)

                    //After success register transfer to logIn page
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                }else{
                    Constants.showErrorSnackBar(task.exception!!.message.toString(), true , context, view)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}