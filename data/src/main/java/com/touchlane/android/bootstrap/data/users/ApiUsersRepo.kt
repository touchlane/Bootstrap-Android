package com.touchlane.android.bootstrap.data.users

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo

class ApiUsersRepo(
    private val usersDataSource: UsersDataSource,
    private val usersMapper: Mapper<List<UserDto>, List<User>>
) : UsersRepo {

    override suspend fun users(): List<User> = usersDataSource.users().let { usersMapper.map(it) }
}