package com.touchlane.android.bootstrap.data.users

interface UsersDataSource {

    suspend fun users(): List<UserDto>
}