package com.touchlane.android.bootstrap.domain.posts

interface PostsRepo {
    suspend fun posts(): List<Post>
}