package com.example.bridal.ui.forgetpasswordfragment

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.bridal.R
import com.example.bridal.util.Constants
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordViewModel : ViewModel(){


    val etEmail = MutableLiveData<String>("")


    fun forgetPassword( context: Context , view : View , progressBar: ProgressBar){

        val email1 = etEmail.value!!.trim { it <= ' ' }
        if (email1.isEmpty()) {
            Constants.showErrorSnackBar(context.resources.getString(R.string.err_msg_erremail), true , context,view)
        } else {
            progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance().sendPasswordResetEmail(email1).addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    Toast.makeText(context,context.resources.getString(R.string.emailsnt), Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE

                    // after finish operation will go login page.
                    Navigation.findNavController(view).navigate(R.id.action_forgetPasswordFragment_to_loginFragment)

                }else{
                    Constants.showErrorSnackBar(task.exception!!.message.toString(),true , context,view)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}