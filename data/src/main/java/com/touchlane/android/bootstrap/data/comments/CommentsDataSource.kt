package com.touchlane.android.bootstrap.data.comments

import io.reactivex.rxjava3.core.Single

interface CommentsDataSource {

    fun commentsToPost(postId: Int): Single<List<CommentDto>>
}