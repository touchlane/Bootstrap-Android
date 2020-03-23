package com.touchlane.android.bootstrap.domain.comments

import com.touchlane.android.bootstrap.domain.Result

class CommentsInteractorImpl(
    private val commentsRepo: CommentsRepo
) : CommentsInteractor {

    override suspend fun commentsToPost(postId: Int): Result<List<Comment>> = try {
        commentsRepo
            .commentsForPost(postId)
            .let { Result.Success(it) }
    } catch (e: Throwable) {
        Result.Error(e)
    }
}