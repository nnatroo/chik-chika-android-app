package com.example.chik_chika

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTweetAdapter (private val list: List<Tweets>) :
        RecyclerView.Adapter<RecyclerViewTweetAdapter.TweetViewHolder>() {
        class TweetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        val imageViewPicture : ImageView
        val textViewName : TextView
        val textViewText : TextView

        init {
            imageViewPicture = itemView.findViewById(R.id.imageViewPicture)
            textViewName = itemView.findViewById(R.id.textViewName)
            textViewText = itemView.findViewById(R.id.textViewText)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_feed,parent, false)
        return TweetViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val

    }

    override fun getItemCount() = list.size

}
