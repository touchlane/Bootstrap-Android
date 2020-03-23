package com.touchlane.android.bootstrap.data.users

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiUsersDataSource(
    private val httpClient: HttpClient,
    private val usersUrl: String
) : UsersDataSource {

    override suspend fun users(): List<UserDto> = httpClient.get(usersUrl)
}