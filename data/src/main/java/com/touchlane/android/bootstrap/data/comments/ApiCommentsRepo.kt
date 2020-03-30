package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo
import io.reactivex.rxjava3.core.Single

class ApiCommentsRepo(
    private val commentsDataSource: CommentsDataSource,
    private val mapper: Mapper<List<CommentDto>, List<Comment>>
) : CommentsRepo {

    override fun commentsForPost(postId: Int): Single<List<Comment>> =
        commentsDataSource
            .commentsToPost(postId)
            .map { mapper.map(it) }
}