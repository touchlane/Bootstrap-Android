package com.touchlane.android.bootstrap.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.domain.comments.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsAdapter(
    itemCallback: DiffUtil.ItemCallback<Comment> = object : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem == newItem
    }
) : ListAdapter<Comment, CommentsAdapter.CommentViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
            .let {
                CommentViewHolder(it)
            }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.showComment(getItem(position))
    }

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name = itemView.name
        private val body = itemView.body

        fun showComment(comment: Comment) {
            name.text = comment.name
            body.text = comment.body
        }
    }
}