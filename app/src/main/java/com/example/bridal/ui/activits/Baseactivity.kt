package com.example.bridal.ui.activits

import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bridal.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class Baseactivity : AppCompatActivity() {

      private lateinit var mProgressDialog : Dialog
      private var doublebacktoext = false

    fun showerr(message: String, errormessage : Boolean){
        val snack = Snackbar.make(findViewById(android.R.id.content) , message ,Snackbar.LENGTH_LONG)
        val snackview = snack.view
        if (errormessage){
            snackview.setBackgroundColor(ContextCompat.getColor(this@Baseactivity, R.color.err))
        }else{snackview.setBackgroundColor(ContextCompat.getColor(this@Baseactivity, R.color.page))
        }
        snack.show()
    }
    fun shwprogressdialog(text:String){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.textView5.text = text
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }
    fun hidepprogreedialog() {
        mProgressDialog.dismiss()
    }
    fun doublebacktoex(){
        if (doublebacktoext){
            super.onBackPressed()
            return
        }
        this.doublebacktoext = true
        Toast.makeText(this, resources.getString(R.string.plsck), Toast.LENGTH_SHORT).show()
        android.os.Handler().postDelayed({
           doublebacktoext  = false     },2000)

    }

}