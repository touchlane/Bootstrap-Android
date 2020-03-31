package com.touchlane.android.bootstrap.domain.posts

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)