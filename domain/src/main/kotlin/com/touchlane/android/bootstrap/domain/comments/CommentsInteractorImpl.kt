package com.touchlane.android.bootstrap.domain.comments

import io.reactivex.rxjava3.core.Single

class CommentsInteractorImpl(
    private val commentsRepo: CommentsRepo
) : CommentsInteractor {

    override fun commentsToPost(postId: Int): Single<List<Comment>> =
        commentsRepo.commentsForPost(postId)
}