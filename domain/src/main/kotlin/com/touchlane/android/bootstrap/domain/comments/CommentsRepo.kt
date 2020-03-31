package com.touchlane.android.bootstrap.domain.comments

import io.reactivex.rxjava3.core.Single

interface CommentsRepo {

    fun commentsForPost(postId: Int): Single<List<Comment>>
}