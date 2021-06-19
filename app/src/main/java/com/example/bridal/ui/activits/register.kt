package com.example.bridal.ui.activits

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.bridal.R
import com.example.bridal.firestore.firestoreclass
import com.example.bridal.model.user
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class register : Baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        login.setOnClickListener {

            onBackPressed()
        }
        register2.setOnClickListener {
            registeruser1()
        }

    }


    private fun validate(): Boolean {
        return when {
            TextUtils.isEmpty(editTextTextPersonName2.text.toString().trim { it <= ' ' }) -> {
                showerr(resources.getString((R.string.err_msg_firstname)),true)
                false
            }
            TextUtils.isEmpty(editTextTextPersonName3.text.toString().trim{ it <= ' ' }) -> {
                showerr(resources.getString((R.string.err_msg_lastname)),true)
                false
            }
            TextUtils.isEmpty(editTextTextEmailAddress3.text.toString().trim { it <= ' ' }) -> {
                showerr(resources.getString((R.string.err_msg_erremail)),true)
                false
            }
            TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' ' }) -> {
                showerr(resources.getString((R.string.err_msg_pass1)),true)
                false


            }
            TextUtils.isEmpty(editTextTextPassword2.text.toString().trim { it <= ' ' })  -> {
                showerr(resources.getString((R.string.err_msg_confirmpass)),true)
                false
            }
            editTextTextPassword.text.toString().trim { it <= ' ' } != editTextTextPassword2.text.toString().trim { it <= ' ' } -> {
                showerr(resources.getString((R.string.err_msg_notmatched)),true)
                false
            }
            else -> {
                true
            }
        }
    }
  
    private fun registeruser1 (){
        if(validate()){
            shwprogressdialog(resources.getString(R.string.plswt))

            val email:String = editTextTextEmailAddress3.text.toString().trim { it <= ' '  }
            val password:String = editTextTextPassword.text.toString().trim { it <= ' '  }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email , password).addOnCompleteListener(
                OnCompleteListener<AuthResult>{ task ->
                    if (task.isSuccessful){
                        val firebaseUser : FirebaseUser = task.result!!.user!!
                        val user1 = user(
                            firebaseUser.uid,
                            editTextTextPersonName2.text.toString().trim { it <= ' ' },
                            editTextTextPersonName3.text.toString().trim{ it <= ' ' },
                            editTextTextEmailAddress3.text.toString().trim{ it <= ' ' },
                            editTextTextPassword.text.toString().trim { it <= ' ' },

                        )
                        firestoreclass().registeruser(this,user1)

                        showerr(resources.getString(R.string.tm)+ "${firebaseUser.uid}",true)
                        //FirebaseAuth.getInstance().signOut()
                       // finish()
                    }else {
                        hidepprogreedialog()
                        showerr((task.exception!!.message.toString()),true)
                    }
                }
            )
        }

    }
    fun useregistersucces(){
        hidepprogreedialog()
        Toast.makeText(this, R.string.tm,Toast.LENGTH_SHORT ).show()

    }
}