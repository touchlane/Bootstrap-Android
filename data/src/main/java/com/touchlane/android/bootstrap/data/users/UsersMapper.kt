package com.touchlane.android.bootstrap.data.users

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.users.User

class UsersMapper : Mapper<List<UserDto>, List<User>> {

    override fun map(input: List<UserDto>): List<User> = input.map { it.toUser() }
}