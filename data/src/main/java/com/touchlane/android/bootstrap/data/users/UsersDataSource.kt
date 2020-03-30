package com.touchlane.android.bootstrap.data.users

import io.reactivex.rxjava3.core.Single

interface UsersDataSource {

    fun users(): Single<List<UserDto>>
}