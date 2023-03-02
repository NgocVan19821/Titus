package com.example.titus.features.messenger.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.titus.R
import com.example.titus.models.Messenger
import kotlinx.android.synthetic.main.item_chat.view.*

class MessengerAdapter(
    val context: Context,
    val idUser: Int,
    val list: MutableList<Messenger>
): RecyclerView.Adapter<MessengerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        if (item.id == idUser){
            holder.itemView.receiveText.isGone = true
            holder.itemView.sendText.text = item.text
        }else{
            holder.itemView.sendText.isGone = true
            holder.itemView.receiveText.text = item.text
        }

        holder.itemView.sendText.setOnLongClickListener {
            if (holder.itemView.emotionView.isGone){
                holder.itemView.emotionView.isGone = false
                holder.itemView.emotionBy.text = "$idUser love it"
            }else
                holder.itemView.emotionView.isGone = true

            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}