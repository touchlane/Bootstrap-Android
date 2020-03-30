package com.touchlane.android.bootstrap.data.users

import com.touchlane.android.bootstrap.data.api.WebApi
import io.reactivex.rxjava3.core.Single

class ApiUsersDataSource(
    private val api: WebApi
) : UsersDataSource {

    override fun users(): Single<List<UserDto>> = api.fetchUsers()
}