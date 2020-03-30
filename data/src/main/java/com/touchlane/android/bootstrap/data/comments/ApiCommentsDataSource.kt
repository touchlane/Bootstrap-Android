package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.data.api.WebApi
import io.reactivex.rxjava3.core.Single

class ApiCommentsDataSource(
    private val webApi: WebApi
) : CommentsDataSource {

    override fun commentsToPost(postId: Int): Single<List<CommentDto>> =
        webApi.fetchComments(postId)
}