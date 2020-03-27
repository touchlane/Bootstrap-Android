package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo

class ApiCommentsRepo(
    private val commentsDataSource: CommentsDataSource,
    private val mapper: Mapper<List<CommentDto>, List<Comment>>
) : CommentsRepo {

    override suspend fun commentsForPost(postId: Int) = commentsDataSource.commentsToPost(postId)
        .let { mapper.map(it) }
}