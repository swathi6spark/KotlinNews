package com.swathi.kotlinnews.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import com.swathi.kotlinnews.R
import com.swathi.kotlinnews.RecyclerView.KotlinFeedAdapter.KotlinFeedViewHolder
import com.swathi.kotlinnews.Retrofit.KotlinFeed

class KotlinFeedAdapter(private val articleList: List<KotlinFeed.FeedData.Article>) : RecyclerView.Adapter<KotlinFeedAdapter.KotlinFeedViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun onItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    class KotlinFeedViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        var title_textView: TextView
        var thumbnail_imageView: ImageView

        init {
            title_textView = itemView.findViewById(R.id.title_textView)
            thumbnail_imageView = itemView.findViewById(R.id.title_image)

            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinFeedViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return KotlinFeedViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: KotlinFeedViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.title_textView.text = currentItem.articleData!!.title
        if (currentItem.articleData!!.thumbnail == "") {
            holder.thumbnail_imageView.visibility = View.GONE
        } else {
            holder.thumbnail_imageView.visibility = View.VISIBLE
            Picasso.get().load(currentItem.articleData!!.thumbnail).into(holder.thumbnail_imageView)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }


}


