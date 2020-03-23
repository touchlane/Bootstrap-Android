package com.touchlane.android.bootstrap.domain.comments

interface CommentsRepo {

    suspend fun commentsForPost(postId: Int): List<Comment>
}