package com.touchlane.android.bootstrap.domain.userpost

import io.reactivex.rxjava3.core.Single

interface UserPostsInteractor {

    fun userPosts(): Single<List<UserPost>>
}