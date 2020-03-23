package com.touchlane.android.bootstrap.data.posts

import com.google.gson.annotations.SerializedName
import com.touchlane.android.bootstrap.domain.posts.Post

data class PostDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)

fun PostDto.toPost(): Post = Post(
    id, userId, title, body
)