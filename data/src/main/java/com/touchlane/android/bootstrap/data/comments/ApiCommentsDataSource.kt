package com.touchlane.android.bootstrap.data.comments

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiCommentsDataSource(
    private val httpClient: HttpClient,
    private val commentsToPostUrl: String
) : CommentsDataSource {

    override suspend fun commentsToPost(postId: Int): List<CommentDto> =
        httpClient.get("$commentsToPostUrl$postId")
}