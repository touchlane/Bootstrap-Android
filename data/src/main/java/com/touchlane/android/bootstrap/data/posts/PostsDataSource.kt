package com.touchlane.android.bootstrap.data.posts

import io.reactivex.rxjava3.core.Single

interface PostsDataSource {
    fun posts(): Single<List<PostDto>>
}