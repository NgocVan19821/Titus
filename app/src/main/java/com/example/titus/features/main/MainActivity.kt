package com.example.titus.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.titus.R
import com.example.titus.commons.extensions.constant.Application
import com.example.titus.commons.extensions.constant.Database
import com.example.titus.commons.extensions.constant.Key
import com.example.titus.commons.extensions.getCurrentTime
import com.example.titus.features.main.fragments.addnews.AddNewsFragment
import com.example.titus.features.main.fragments.home.HomeFragment
import com.example.titus.features.main.fragments.menu.MenuFragment
import com.example.titus.features.main.fragments.notification.NotificationFragment
import com.example.titus.features.main.fragments.search.SearchFragment
import com.example.titus.features.messenger.MessengerActivity
import com.example.titus.models.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpEventListenerHeader()
        loadFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.addNews -> {
                    loadFragment(AddNewsFragment())
                    true
                }
                R.id.notification -> {
                    loadFragment(NotificationFragment())
                    true
                }
                else -> {
                    loadFragment(MenuFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    private fun setUpEventListenerHeader() {
        intentMakeFriendButton.setOnClickListener {
            val idPost = "${Application.idUser}-postBy-${Key.autoID.getCurrentTime()}"
            val news = hashMapOf(
                Database.idPost to idPost,
                Database.textPost to "Toi ten tung hahah",
                Database.mediasPost to mutableListOf(
                    LinkImage(Application.avatarUser)
                ),
                Database.qualitiesReact to 0,
                Database.avatarAuthor to Application.avatarUser,
                Database.nameAuthor to "${Application.firstName} ${Application.lastName}",
                Database.dateUpPost to Key.getDMY.getCurrentTime()
            )
            Application.dbFirebase
                .collection(Database.pathPostNew)
                .document(idPost)
                .set(news)
        }

        intentMessengerButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, MessengerActivity::class.java))
        }
    }
}