package com.touchlane.android.bootstrap.data.users

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import io.reactivex.rxjava3.core.Single

class ApiUsersRepo(
    private val usersDataSource: UsersDataSource,
    private val usersMapper: Mapper<List<UserDto>, List<User>>
) : UsersRepo {

    override fun users(): Single<List<User>> = usersDataSource.users().map {
        usersMapper.map(it)
    }
}