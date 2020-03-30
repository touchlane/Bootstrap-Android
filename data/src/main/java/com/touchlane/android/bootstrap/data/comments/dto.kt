package com.touchlane.android.bootstrap.data.comments

import com.google.gson.annotations.SerializedName
import com.touchlane.android.bootstrap.domain.comments.Comment

data class CommentDto(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)

fun CommentDto.toComment(): Comment = Comment(
    postId, id, name, body
)