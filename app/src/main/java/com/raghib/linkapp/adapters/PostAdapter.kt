package com.raghib.linkapp.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.raghib.linkapp.R
import com.raghib.linkapp.models.Post
import com.raghib.linkapp.utils.TimeUtils

class PostAdapter(options: FirestoreRecyclerOptions<Post>) : FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder>(
    options
) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val userName: TextView=itemView.findViewById(R.id.userName)
        val imgUser:ImageView=itemView.findViewById(R.id.imgUser)
        val postTime:TextView=itemView.findViewById(R.id.postTime)
        val tvPost:TextView=itemView.findViewById(R.id.tvPost)
        val imgLike:ImageView=itemView.findViewById(R.id.imgLikeButton)
        val likeCount:TextView=itemView.findViewById(R.id.tvLikeCount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        return  PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_home_page,parent,false))

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.userName.text=model.user.displayName
        holder.postTime.text=TimeUtils().getTimeAgo(model.createdAt)
        holder.likeCount.text=model.likedBy.size.toString()
        holder.tvPost.text=model.text
        Glide.with(holder.imgUser.context).load(model.user.imgUrl).circleCrop().into(holder.imgUser)

    }


}