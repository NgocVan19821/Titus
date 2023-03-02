package com.example.titus.commons

import android.app.Activity
import android.content.Intent

fun Activity.intentTo(to: Activity){
    startActivity(Intent(this, to::class.java))
}
