package com.touchlane.android.bootstrap.domain.comments

import io.reactivex.rxjava3.core.Single

interface CommentsInteractor {

    fun commentsToPost(postId: Int): Single<List<Comment>>
}