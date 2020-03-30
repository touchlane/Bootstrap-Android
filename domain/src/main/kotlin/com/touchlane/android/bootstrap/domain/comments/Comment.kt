package com.touchlane.android.bootstrap.domain.comments

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val body: String
)