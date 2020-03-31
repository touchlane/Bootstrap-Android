package com.touchlane.android.bootstrap.data.posts

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import io.reactivex.rxjava3.core.Single

class ApiPostsRepo(
    private val postsDataSource: PostsDataSource,
    private val postsMapper: Mapper<List<PostDto>, List<Post>>
) : PostsRepo {

    override fun posts(): Single<List<Post>> = postsDataSource.posts().map {
        postsMapper.map(it)
    }
}