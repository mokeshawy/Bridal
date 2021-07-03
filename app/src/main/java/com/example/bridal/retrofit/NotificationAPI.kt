package com.example.bridal.retrofit


import com.example.bridal.notification.PushNotification
import com.example.bridal.util.Constants.CONTENT_TYPE
import com.example.bridal.util.Constants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification( @Body notification: PushNotification): Response<ResponseBody>
}