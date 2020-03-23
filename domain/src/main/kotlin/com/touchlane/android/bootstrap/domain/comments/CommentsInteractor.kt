package com.touchlane.android.bootstrap.domain.comments

import com.touchlane.android.bootstrap.domain.Result

interface CommentsInteractor {

    suspend fun commentsToPost(postId: Int): Result<List<Comment>>
}