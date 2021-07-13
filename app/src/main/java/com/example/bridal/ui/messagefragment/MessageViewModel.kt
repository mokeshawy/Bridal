package com.example.bridal.ui.messagefragment

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.R
import com.example.bridal.model.ChatListModel
import com.example.bridal.model.ChatModel
import com.example.bridal.model.UserModel
import com.example.bridal.ui.glideLoader
import com.example.bridal.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessageViewModel : ViewModel() {




    var mUsersLiveData         = MutableLiveData<ArrayList<UserModel>>()
    var mUsersArray            = ArrayList<UserModel>()
    var mChatListArray         = ArrayList<ChatListModel>()

    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val userReference       = firebaseDatabase.getReference(Constants.USERS)
    var chatListReference           = firebaseDatabase.getReference(Constants.CHAT_LIST_REFERENCE)
    var chatsReference              = firebaseDatabase.getReference(Constants.CHAT_REFERENCE)
    var lastMessage : String = ""


    // get message list from firebase.
    fun getChatList(context: Context, recycler_view_chatList : RecyclerView, tv_no_message : TextView){
        mChatListArray = ArrayList()
        chatListReference.child(Constants.getCurrentUser()).addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                mChatListArray.clear()
                for( ds in snapshot.children){

                    val chatList = ds.getValue(ChatListModel::class.java)!!

                    mChatListArray.add(chatList)
                }
                //call retrieveChat function
                retrieveChatList(context , recycler_view_chatList , tv_no_message)
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }
        })
    }

    // retrieveChatList from users reference get id equal id from chatList reference
    fun retrieveChatList(context: Context, recycler_view_chatList : RecyclerView, tv_no_message : TextView){
        mUsersArray = ArrayList()
        userReference.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mUsersArray.clear()

                for ( ds in snapshot.children){

                    val userId      = ds.child(Constants.USER_ID).value.toString()
                    val firstName   = ds.child(Constants.FIRST_NAME_KEY).value.toString()
                    val lastName    = ds.child(Constants.LAST_NAME_KEY).value.toString()
                    val email       = ds.child(Constants.USER_EMAIL_KEY).value.toString()
                    val image       = ds.child(Constants.USER_IMAGE_KEY).value.toString()


                    for (chatList in mChatListArray){

                        if(userId == chatList.id){

                            mUsersArray.add(UserModel(userId,firstName,lastName,email,image))
                        }
                    }
                    mUsersLiveData.value = mUsersArray
                }

                if(mUsersArray.size > 0){
                    recycler_view_chatList.visibility   = View.VISIBLE
                    tv_no_message.visibility            = View.GONE
                }else{
                    recycler_view_chatList.visibility   = View.GONE
                    tv_no_message.visibility            = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }
        })
    }

    // function show latest message.
    @SuppressLint("SetTextI18n")
    fun retrieveLastMessage(context: Context , chatUserId : String , tv_last_message : TextView){
        lastMessage = "Default Message"

        chatsReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    val chat = ds.getValue(ChatModel::class.java)!!

                    if( chat.receiverId == Constants.getCurrentUser() && chat.senderId == chatUserId || chat.receiverId == chatUserId &&  chat.senderId == Constants.getCurrentUser()){
                        lastMessage = chat.message
                    }
                }
                when(lastMessage){
                    "Default Message" -> tv_last_message.text = "No Message"
                    "sent you an image." -> tv_last_message.text = "Image Sent."
                    else -> tv_last_message.text = lastMessage
                }
                lastMessage = "Default Message"
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }
        })
    }
}