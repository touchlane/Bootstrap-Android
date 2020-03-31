package com.touchlane.android.bootstrap.data.posts

import com.touchlane.android.bootstrap.data.api.WebApi
import io.reactivex.rxjava3.core.Single

class ApiPostsDataSource(
    private val webApi: WebApi
) : PostsDataSource {

    override fun posts(): Single<List<PostDto>> = webApi.fetchPosts()
}