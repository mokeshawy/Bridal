package com.example.bridal.ui.activits

import android.os.Bundle
import android.widget.Toast
import com.example.bridal.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_frgtbassword.*
//
//class frgtbassword : Baseactivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_frgtbassword)
//        sub.setOnClickListener {
//            val email1: String = textInputLayout.text.toString().trim { it <= ' ' }
//            if (email1.isEmpty()) {
//                showerr(resources.getString(R.string.err_msg_erremail), true)
//            } else {
//                shwprogressdialog(resources.getString(R.string.plswt))
//                FirebaseAuth.getInstance().sendPasswordResetEmail(email1).addOnCompleteListener {
//                    task ->
//                    hidepprogreedialog()
//                    if (task.isSuccessful){
//                        Toast.makeText(this,resources.getString(R.string.emailsnt),Toast.LENGTH_LONG).show()
//                        finish()
//                    }else{
//                        showerr(task.exception!!.message.toString(),true)
//                    }
//                }
//            }
//        }
//    }
//}