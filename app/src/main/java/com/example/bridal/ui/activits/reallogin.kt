package com.example.bridal.ui.activits

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.bridal.R
import com.example.bridal.firestore.firestoreclass
import com.example.bridal.model.user
import com.google.firebase.auth.FirebaseAuth
import custom.constants
import kotlinx.android.synthetic.main.activity_reallogin.*
import java.util.*

@Suppress("DEPRECATION")
class reallogin : Baseactivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLocate() // call LoadLocate

        setContentView(R.layout.activity_reallogin)


        logb.setOnClickListener(this)
        register.setOnClickListener(this)
        frgtpas.setOnClickListener(this)
        button3.setOnClickListener(this)

    }

    private fun showChangeLang() {
        val listItems = arrayOf("عربي", "English")

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("choose language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                setLocate("ar")
                recreate()
            } else if (which == 1) {
                setLocate("en")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language!!)
    }


      private  fun loginRegisterUser() {
            if (validateLoginDetails()) {
                shwprogressdialog(resources.getString(R.string.plswt))
                val email = editTextTextEmailAddress.text.toString().trim { it <= ' ' }
                val password = editTextTextPersonName.text.toString().trim { it <= ' ' }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        firestoreclass().getUserDetails(this@reallogin)
                        showerr(resources.getString(R.string.logsucc), false)

                    } else {
                        hidepprogreedialog()
                        showerr(task.exception!!.message.toString(), true)
                    }
                }
            }
        }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.logb -> {
                    //val intent = Intent(this,dashboard::class.java)
                   // startActivity(intent)
                    loginRegisterUser()

                }
                R.id.register -> {
                    val intent = Intent(this, com.example.bridal.ui.activits.register::class.java)
                    startActivity(intent)
                }
                R.id.button3 -> {
                    showChangeLang()
                }
                R.id.frgtpas -> {
                    val intent1 = Intent(this@reallogin, frgtbassword::class.java)
                    startActivity(intent1)

                }
            }
        }
    }


        private fun validateLoginDetails(): Boolean {
            return when {
                TextUtils.isEmpty(editTextTextEmailAddress.text.toString().trim { it <= ' ' }) -> {
                    showerr(resources.getString((R.string.err_msg_erremail)), false)
                    false
                }
                TextUtils.isEmpty(editTextTextPersonName.text.toString().trim { it <= ' ' }) -> {
                    showerr(resources.getString((R.string.err_msg_pass1)), false)
                    false
                }
                else -> {
                    true
                }
            }
        }
    fun userLoggedInSuccess(user: user) {

        // Hide the progress dialog.
         hidepprogreedialog()
        val intent4 =  Intent(this , MainActivity::class.java)
        intent.putExtra(constants.extrauserdetail,user)

        startActivity(intent4)
        finish()

        // Print the user details in the log as of now.
        Log.i(R.string.fst_name.toString(), user.firstname)
        Log.i(R.string.scnd_name.toString(), user.lastname)
        Log.i(R.string.em.toString(), user.email)
        // Redirect the user to Main Screen after log in.
       // startActivity(intent4)
         //   finish()
       }
    // END
}

