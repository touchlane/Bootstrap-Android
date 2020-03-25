package com.touchlane.android.bootstrap

import android.content.Context
import android.content.Intent
import com.touchlane.android.bootstrap.comments.CommentsActivity

interface Router {
    fun goTo(destination: Destination)
}

class SimpleRouter(
    private val context: Context
) : Router {

    override fun goTo(destination: Destination) {
        destination.land(context)
    }
}

sealed class Destination {

    abstract fun land(context: Context)

    data class Comments(
        val postId: Int
    ) : Destination() {

        override fun land(context: Context) {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(CommentsActivity.EXTRA_POST_ID, postId)
            context.startActivity(intent)
        }
    }
}