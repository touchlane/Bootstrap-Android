package com.touchlane.android.bootstrap.data.posts

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiPostsDataSource(
    private val httpClient: HttpClient,
    private val postsUrl: String
) : PostsDataSource {

    override suspend fun posts(): List<PostDto> = httpClient.get(postsUrl)
}