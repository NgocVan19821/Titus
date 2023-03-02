package com.example.titus.commons.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.logD(){
    Log.d("Result", this)
}

fun String.logDKey(key: String){
    Log.d(key, this)
}

fun String.logE(){
    Log.e("Error", this)
}

fun String.getCurrentTime(): String{
    return when (this){
        "dmy" -> {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            current.format(formatter)
        }
        else -> {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("ddMM:yyyy:HHmmss:SSS")
            current.format(formatter)
        }
    }

}

