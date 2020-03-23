package com.touchlane.android.bootstrap.data.posts

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo

class ApiPostsRepo(
    private val postsDataSource: PostsDataSource,
    private val postsMapper: Mapper<List<PostDto>, List<Post>>
) : PostsRepo {

    override suspend fun posts(): List<Post> = postsDataSource.posts().let { postsMapper.map(it) }
}