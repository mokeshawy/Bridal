package com.example.bridal.ui.chatfragment

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bridal.model.ChatListModel
import com.example.bridal.model.ChatModel
import com.example.bridal.model.UserModel
import com.example.bridal.util.Constants
import com.firebase.ui.auth.data.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatViewModel : ViewModel() {

   val etSendMessage = MutableLiveData<String>("")

   var mChatArrayListLive      = MutableLiveData<ArrayList<ChatModel>>()
   var mChatArrayList          = ArrayList<ChatModel>()

   var mUsersLiveData         = MutableLiveData<ArrayList<UserModel>>()
   var mUsersArray            = ArrayList<UserModel>()
   var mChatListArray         = ArrayList<ChatListModel>()

   private val firebaseDatabase     = FirebaseDatabase.getInstance()
   private var usersReference       = firebaseDatabase.getReference(Constants.USERS)
   private val chatReference        = firebaseDatabase.getReference(Constants.CHAT_REFERENCE)
   var chatListReference            = firebaseDatabase.getReference(Constants.CHAT_LIST_REFERENCE)
   var chatsListReceiverRef         = firebaseDatabase.getReference(Constants.CHAT_LIST_REFERENCE)

   // fun send message
   fun sendMessage(receiveUserId : String, view : View, et_message : EditText){
      if(etSendMessage.value!!.isEmpty()){
         Snackbar.make( view , "Message is Empty" , Snackbar.LENGTH_SHORT).show()
         et_message.setText("")
      }else{
         val pushKey = chatReference.push().key.toString()
         val map = HashMap<String , Any>()
         map[Constants.CHAT_SENDER_ID]    = Constants.getCurrentUser()
         map[Constants.CHAT_RECEIVER_ID]  = receiveUserId
         map[Constants.CHAT_MESSAGE]      = etSendMessage.value!!.toString()
         map[Constants.CHAT_PUSH_KEY]     = pushKey

         chatReference.child(pushKey).setValue(map).addOnCompleteListener { task ->
            if(task.isSuccessful){
               chatListReference.child(Constants.getCurrentUser()).child(receiveUserId).child(Constants.CHAT_LIST_ID).setValue(receiveUserId)
               chatsListReceiverRef.child(receiveUserId).child(Constants.getCurrentUser()).child(Constants.CHAT_LIST_ID).setValue(Constants.getCurrentUser())
            }
         }
         et_message.setText("")

      }
   }


   // function retrieve message
   fun retrieveMessage( context: Context,
                        userSenderId : String,
                        userReceiverId : String){

      mChatArrayList = ArrayList()

      chatReference.addValueEventListener( object : ValueEventListener{
         override fun onDataChange(snapshot: DataSnapshot) {
            mChatArrayList.clear()
            for ( ds in snapshot.children){

               var chat = ds.getValue(ChatModel::class.java)!!

               if( chat.receiverId == userSenderId && chat.senderId == userReceiverId || chat.receiverId == userReceiverId && chat.senderId == userSenderId){

                  mChatArrayList.add(chat)
               }
            }
            mChatArrayListLive.value = mChatArrayList
         }

         override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context , error.message ,Toast.LENGTH_SHORT).show()
         }
      })
   }
}