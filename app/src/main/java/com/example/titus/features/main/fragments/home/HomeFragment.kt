package com.example.titus.features.main.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.titus.commons.extensions.constant.Application
import com.example.titus.features.main.fragments.home.adapter.NewsAdapter
import com.example.titus.models.News
import com.example.titus.R
import com.example.titus.commons.extensions.constant.Database
import com.example.titus.commons.extensions.logD
import com.example.titus.commons.extensions.logDKey
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), NewsAdapter.NewsAdapterInterface {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchNewFeeds()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        list_item_new_feed.adapter?.notifyItemRangeChanged(0, Application.listNewFeeds.count())
    }

    //    MARK: Functions
//    --init RecycleView
    private fun setUpRecycleView() {
        list_item_new_feed.adapter = NewsAdapter(requireActivity(), this, Application.listNewFeeds)
    }

    //    --init Event Listener
    @SuppressLint("NotifyDataSetChanged")
    private fun fetchNewFeeds() {
        setUpRecycleView()
        Application.dbFirebase
            .collection(Database.pathPostNew)
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    Application.listNewFeeds.clear()
                    for (data in it.documents) {
                        val new = data.toObject(News::class.java)
                        if (new != null)
                            Application.listNewFeeds.add(new)
                    }
                    Application.listNewFeeds.reverse()
                    setUpRecycleView()
                }
            }
    }

//    Interface news
    override fun didClickReaction(idPost: String) {
        "Click here".logD()
        Application.dbFirebase.collection(Database.pathReact(idPost))
            .document(Application.idUser)
            .get()
            .addOnSuccessListener {
                if (it.exists()){
                    Application.dbFirebase
                        .collection(Database.pathReact(idPost))
                        .document(Application.idUser)
                        .delete()
                }else{
                    val comment = hashMapOf(
                        Database.nameReact to "${Application.firstName} ${Application.lastName}",
                        Database.avatarReact to Application.avatarUser
                    )
                    Application.dbFirebase
                        .collection(Database.pathReact(idPost))
                        .document(Application.idUser)
                        .set(comment)
                }
            }
    }

    override fun didClickDetailPost(idPost: String) {

    }
}