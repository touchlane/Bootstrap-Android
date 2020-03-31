package com.touchlane.android.bootstrap.domain.posts

import io.reactivex.rxjava3.core.Single

interface PostsRepo {

    fun posts(): Single<List<Post>>
}