package com.touchlane.android.bootstrap.domain.users

interface UsersRepo {

    suspend fun users(): List<User>
}