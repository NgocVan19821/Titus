package com.example.titus.models

data class Notification(
    val title: String,
    val messenger: String,
    val icon: String,
    val image: String
)

data class PushNotification(
    val data: Notification,
    val toUser: String
)
