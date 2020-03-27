package com.touchlane.android.bootstrap.data.posts

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.posts.Post

class PostsMapper : Mapper<List<PostDto>, List<Post>> {

    override fun map(input: List<PostDto>): List<Post> = input.map { it.toPost() }
}