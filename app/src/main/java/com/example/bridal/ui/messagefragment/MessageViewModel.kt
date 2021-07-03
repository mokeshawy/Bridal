package com.example.bridal.ui.messagefragment

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bridal.R
import com.example.bridal.model.UserModel
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessageViewModel : ViewModel() {

    val mUserLiveArrayList  =  MutableLiveData<ArrayList<UserModel>>()
    val mUserArrayList      : ArrayList<UserModel> = ArrayList()

    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val userReference       = firebaseDatabase.getReference(Constants.USERS)

    //fun get data for users for database
    fun getUser(context: Context){

        userReference.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mUserArrayList.clear()
                // for loop for get all data for user form database
                for (ds in snapshot.children){

                    val userId  = ds.child(Constants.USER_ID).value.toString()
                    val firstName = ds.child(Constants.FIRST_NAME_KEY).value.toString()
                    val lastName = ds.child(Constants.LAST_NAME_KEY).value.toString()
                    val image = ds.child(Constants.USER_IMAGE_KEY).value.toString()


                    // check for show all data for users with out user login
                    if(userId != Constants.getCurrentUser()){
                        mUserArrayList.add(UserModel(userId,firstName,lastName,image))
                    }
                }
                mUserLiveArrayList.value = mUserArrayList
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }

        })
    }
}