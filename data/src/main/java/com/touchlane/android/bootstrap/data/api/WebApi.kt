package com.touchlane.android.bootstrap.data.api

import com.touchlane.android.bootstrap.data.comments.CommentDto
import com.touchlane.android.bootstrap.data.posts.PostDto
import com.touchlane.android.bootstrap.data.users.UserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("users")
    fun fetchUsers(): Single<List<UserDto>>

    @GET("posts")
    fun fetchPosts(): Single<List<PostDto>>

    @GET("comments")
    fun fetchComments(@Query("postId") postId: Int): Single<List<CommentDto>>
}