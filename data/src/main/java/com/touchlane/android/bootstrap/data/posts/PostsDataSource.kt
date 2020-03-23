package com.touchlane.android.bootstrap.data.posts

interface PostsDataSource {
    suspend fun posts(): List<PostDto>
}