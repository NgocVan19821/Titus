package com.example.titus.features.main.fragments.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.titus.R
import com.example.titus.commons.extensions.constant.Application
import com.example.titus.commons.extensions.constant.Database
import com.example.titus.commons.extensions.logD
import com.example.titus.commons.extensions.logDKey
import com.example.titus.models.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(
    val context: Context,
    private var listener: NewsAdapterInterface,
    val list: MutableList<News>
) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    interface NewsAdapterInterface {
        fun didClickReaction(idPost: String)
        fun didClickDetailPost(idPost: String)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        isReact(item.idPost.toString(), holder.itemView.react_post_button, holder.itemView.item_news)
        Glide.with(context).load(item.avatarAuthor).into(holder.itemView.avatar_user)
        if (!item.mediasPost.isNullOrEmpty())
            Glide.with(context).load(item.mediasPost?.first()?.link)
                .into(holder.itemView.content_image_post)
        else
            holder.itemView.content_image_post.isGone = true

        holder.itemView.name_user.text = item.nameAuthor
        holder.itemView.time_posted.text = item.dateUpPost
        holder.itemView.content_text_post.text = item.textPost
//            holder.itemView.total_react.text = if (item.listReact?.total!! < 2) "${item.listReact?.total} like" else "${item.listReact?.total} likes"

//        Events
        holder.itemView.react_post_button.setOnClickListener {
            listener.didClickReaction(item.idPost.toString())
            if (holder.itemView.react_post_button.tag == 0){
                Glide.with(context).load(R.drawable.ic_red_heart).into(holder.itemView.react_post_button)
                holder.itemView.react_post_button.tag = 1
            }else{
                Glide.with(context).load(R.drawable.ic_no_react_red_heart).into(holder.itemView.react_post_button)
                holder.itemView.react_post_button.tag = 0
            }
        }

        holder.itemView.main_content_view.setOnClickListener {
            listener.didClickDetailPost(item.idPost.toString())
        }
    }

    override fun getItemCount(): Int {
        list.size.toString().logD()
        return list.size
    }

    private fun isReact(idPost: String, display: ImageView, itemView: RelativeLayout) {
        Application.dbFirebase.collection(Database.pathReact(idPost))
            .document(Application.idUser)
            .get()
            .addOnSuccessListener {
                if (it.exists()){
                    Glide.with(context).load(R.drawable.ic_red_heart).into(display)
                    display.tag = 1
                } else{
                    Glide.with(context).load(R.drawable.ic_no_react_red_heart).into(display)
                    display.tag = 0
                }
                display.isVisible = true
                itemView.isVisible = true
            }
    }
}