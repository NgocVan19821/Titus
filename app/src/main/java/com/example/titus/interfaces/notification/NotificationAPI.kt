package com.example.titus.interfaces.notification

import com.example.titus.commons.extensions.constant.Code
import com.example.titus.commons.extensions.constant.Key
import com.example.titus.models.PushNotification
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface
NotificationAPI {

    @Headers("Authorization: key=${Key.firebaseServer}", "Content-Type:${Code.CODE_TYPE_REQUEST}")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Call<ResponseBody>
}