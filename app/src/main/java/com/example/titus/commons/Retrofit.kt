package com.example.titus.commons

import com.example.titus.commons.extensions.constant.Application
import com.example.titus.interfaces.notification.NotificationAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Application.fcmBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NotificationAPI::class.java)
        }
    }


}