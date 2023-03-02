package com.example.titus.commons.extensions.constant

import com.example.titus.models.News
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Application {

    companion object{
        val dbFirebase = Firebase.firestore


        val user1 = "e2a8VKaBRy--yC31YUt8ig:APA91bFS83hP7tI5WiP6R2bSh3WDcTK2Lw1RfTTdGIY9e9Ib-kQy2XnawT2iXGn4D4fwUfbf_hhA20VEE1xy4_oj_JbPc9-rWTfvP_oLqQG6MGHuEDQGQiK7ou4RhTPSKPiYY4dImbON"
        val user2 = "f9BVcYC_S1ePebuzXlx5T3:APA91bEbQ4QJkH3XLyqJL18Iu3rKi3K9H90GiijyX2AxXXrgFdfmchimBDb_5hhi-Vgl_TrRJZiDmtpICR1gWGoDRH6DhikVyBxmuiRBWL7KdwuIm6xfnJPRnQFQ5gGB85YujxxdIygx"

        val idUser = "0905045802"
        val avatarUser = "https://www.seatemperatu.re/site/images/illustration/vietnam_770.jpg"
        val firstName = "Tung"
        val lastName = "Le"

        const val fcmBaseURL = "https://fcm.googleapis.com"
        var listNewFeeds = mutableListOf<News>()
    }
}