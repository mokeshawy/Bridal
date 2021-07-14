package com.example.bridal.ui.activits


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bridal.notification.NotificationData
import com.example.bridal.retrofit.RetrofitInstance
import com.example.bridal.R
import com.example.bridal.databinding.ActivityNotificationBinding
import com.example.bridal.notification.PushNotification
import com.example.bridal.util.Constants
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotificationActivity : AppCompatActivity(){

    val TAG = "NotificationActivity"
    lateinit var binding : ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)

        // hide action bar.
        supportActionBar!!.hide()

        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)
        binding.btnSend.setOnClickListener {
            val title   = binding.etTitle.text.toString()
            val message = binding.etMessage.text.toString()
            //val recipientToken = etToken.text.toString()
            if(title.isNotEmpty() && message.isNotEmpty() ){
                PushNotification(
                    NotificationData(title, message),Constants.TOPIC

                ).also {
                    sendNotification(it)
                }
            }
        }
    }
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Toast.makeText(this@NotificationActivity,"Push" , Toast.LENGTH_SHORT).show()
                //Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Toast.makeText(this@NotificationActivity,response.errorBody().toString() , Toast.LENGTH_SHORT).show()
                //Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}