package com.touchlane.android.bootstrap.data.users

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class ApiUsersRepoTest {

    private lateinit var repo: UsersRepo
    private val mockDataSource = mock<UsersDataSource>()
    private val mockMapper = mock<Mapper<List<UserDto>, List<User>>>()
    private val mockUserDtos = mock<List<UserDto>>()
    private val mockUsers = mock<List<User>>()

    @Before
    fun setup() {
        repo = ApiUsersRepo(mockDataSource, mockMapper)
    }

    @Test
    fun testUsers() {
        whenever(mockDataSource.users()).thenReturn(Single.just(mockUserDtos))
        whenever(mockMapper.map(mockUserDtos)).thenReturn(mockUsers)

        repo.users()
            .test()
            .assertValue(mockUsers)

        verify(mockDataSource).users()
        verify(mockMapper).map(mockUserDtos)
    }
}