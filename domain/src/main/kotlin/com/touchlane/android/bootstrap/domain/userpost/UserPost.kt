package com.touchlane.android.bootstrap.domain.userpost

data class UserPost(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val username: String,
    val email: String
)