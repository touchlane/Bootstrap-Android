package com.touchlane.android.bootstrap.domain.userposts

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.domain.ResourceNotFoundException
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserPostsInteractorImplTest {

    private val testPost1 = Post(1, 1, "title1", "body1")
    private val testPost2 = Post(2, 2, "title2", "body2")
    private val testUser1 = User(1, "name1", "username1", "email1", "phone1")
    private val testUser2 = User(2, "name2", "username2", "email2", "phone2")
    private val testUserPost1 = UserPost(1, 1, "title1", "body1", "username1", "email1")
    private val testUserPost2 = UserPost(2, 2, "title2", "body2", "username2", "email2")

    private lateinit var interactor: UserPostsInteractor

    private val mockPostsRepo = mock<PostsRepo>()
    private val mockUsersRepo = mock<UsersRepo>()

    @Before
    fun setup() {
        interactor = UserPostsInteractorImpl(mockPostsRepo, mockUsersRepo)
    }

    @Test
    fun testFetchUserPosts() = runBlocking {
        whenever(mockPostsRepo.posts()).thenReturn(listOf(testPost1, testPost2))
        whenever(mockUsersRepo.users()).thenReturn(listOf(testUser1, testUser2))

        val result = interactor.userPosts()

        assertTrue(result is Result.Success)
        val success = result as Result.Success
        assertEquals(testUserPost1, success.value[0])
        assertEquals(testUserPost2, success.value[1])
    }

    @Test
    fun testFetchUserPostsRequestFails() = runBlocking {
        whenever(mockPostsRepo.posts()).thenThrow(RuntimeException("TEST"))
        whenever(mockUsersRepo.users()).thenReturn(listOf(testUser1, testUser2))

        val result = interactor.userPosts()

        assertTrue(result is Result.Error)
    }

    @Test
    fun testFetchUserPostsUserNotFound() = runBlocking {
        whenever(mockPostsRepo.posts()).thenReturn(listOf(testPost1, testPost2))
        whenever(mockUsersRepo.users()).thenReturn(listOf(testUser1))

        val result = interactor.userPosts()

        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).throwable is ResourceNotFoundException)
    }
}