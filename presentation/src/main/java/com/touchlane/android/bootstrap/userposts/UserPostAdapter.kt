package com.touchlane.android.bootstrap.userposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.domain.userpost.UserPost
import kotlinx.android.synthetic.main.item_user_post.view.*

class UserPostAdapter(
    private val onClick: (UserPost) -> Unit,
    itemCallback: DiffUtil.ItemCallback<UserPost> = object : DiffUtil.ItemCallback<UserPost>() {
        override fun areItemsTheSame(oldItem: UserPost, newItem: UserPost): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserPost, newItem: UserPost): Boolean =
            oldItem == newItem
    }
) : ListAdapter<UserPost, UserPostAdapter.UserPostViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_post, parent, false)
            .let {
                UserPostViewHolder(it)
            }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        val userPost = getItem(position)
        holder.showUserPost(userPost)
        holder.itemView.setOnClickListener {
            onClick(userPost)
        }
    }

    class UserPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = itemView.title
        private val body = itemView.body
        private val username = itemView.username

        fun showUserPost(userPost: UserPost) {
            title.text = userPost.title
            body.text = userPost.body
            username.text = userPost.username
        }
    }
}