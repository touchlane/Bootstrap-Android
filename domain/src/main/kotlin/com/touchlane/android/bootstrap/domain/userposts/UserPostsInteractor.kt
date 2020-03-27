package com.touchlane.android.bootstrap.domain.userposts

import com.touchlane.android.bootstrap.domain.Result

interface UserPostsInteractor {

    suspend fun userPosts(): Result<List<UserPost>>
}