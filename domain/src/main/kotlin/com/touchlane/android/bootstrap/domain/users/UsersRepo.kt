package com.touchlane.android.bootstrap.domain.users

import io.reactivex.rxjava3.core.Single

interface UsersRepo {

    fun users(): Single<List<User>>
}