package com.touchlane.android.bootstrap.data.comments

interface CommentsDataSource {

    suspend fun commentsToPost(postId: Int): List<CommentDto>
}