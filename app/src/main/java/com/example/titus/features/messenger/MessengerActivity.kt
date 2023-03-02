package com.example.titus.features.messenger

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.titus.R
import com.example.titus.commons.Retrofit
import com.example.titus.commons.extensions.constant.Application
import com.example.titus.commons.extensions.logD
import com.example.titus.commons.extensions.logE
import com.example.titus.features.messenger.adapter.MessengerAdapter
import com.example.titus.models.Messenger
import com.example.titus.models.Notification
import com.example.titus.models.PushNotification
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_messenger.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MessengerActivity : AppCompatActivity() {

    //    MARK: Properties
    private lateinit var chatDatabase: DatabaseReference
    private var idUser = 1
    private var chatList = mutableListOf<Messenger>()

    //    MARK: Lifecycle - MAIN
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)

//        init

        this.setUpFirebase()
        this.setUpListener()
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/myTopic2")
//        set up
        this.fetchDataSlot()
        this.getTokenAccount()
    }

//    MARK: Functions

    //    -- init RecycleView
    private fun setUpRecycleView() {
        list_item_chat.isNestedScrollingEnabled = false
        list_item_chat.adapter = MessengerAdapter(this, this.idUser, chatList)

    }

    //    -- init Firebase
    private fun setUpFirebase() {
        chatDatabase = FirebaseDatabase.getInstance().getReference("Messenger")
    }

    //    -- init Listener
    @SuppressLint("NotifyDataSetChanged")
    private fun setUpListener() {
        sendButton.setOnClickListener {
            if (contentEditText.text.isNotEmpty()) {
                addChatToReceiver(this.idUser, contentEditText.text.toString())

                list_item_chat.layoutManager?.scrollToPosition(
                    list_item_chat.adapter?.itemCount!!.minus(
                        1
                    )
                )


                PushNotification(
                    Notification("New messenger", contentEditText.text.toString(), "", ""), Application.user1
                ).also {
                    this.sendNotification(it)
                }
                contentEditText.setText("")
            }
        }
    }

    //    -- fetch a conversation
    private fun fetchDataSlot() {
        chatDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    chatList.clear()
                    for (productSnapshot in snapshot.children) {
                        val item = productSnapshot.getValue(Messenger::class.java)
                        chatList.add(item!!)
                    }
                    setUpRecycleView()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

//    -- chat
    private fun addChatToReceiver(id: Int, content: String){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")
        val idText = current.format(formatter)

        chatDatabase.child(idText).setValue(Messenger(id, content))
    }

    private fun getTokenAccount(){
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            it.toString().logD()
            print(it)
        }

    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = Retrofit.api.postNotification(notification)
//            if(response.isSucc) {
//                "Response: ${Gson().toJson(response)}".logD()
//            } else {
//                response.errorBody().toString().logE()
//                "errorHere else".logE()
//            }
        } catch(e: Exception) {
            e.toString().logE()
            "errorHere catch".logE()
        }
    }
}