package com.touchlane.android.bootstrap.domain.userposts

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.domain.ResourceNotFoundException
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.userpost.UserPost
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractor
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractorImpl
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class UserPostsInteractorImplTest {

    private val testPost1 = Post(1, 1, "title1", "body1")
    private val testPost2 = Post(2, 2, "title2", "body2")
    private val testUser1 = User(
        1, "name1", "username1", "email1", "phone1"
    )
    private val testUser2 = User(
        2, "name2", "username2", "email2", "phone2"
    )
    private val testUserPost1 = UserPost(
        1, 1, "title1", "body1", "username1", "email1"
    )
    private val testUserPost2 = UserPost(
        2, 2, "title2", "body2", "username2", "email2"
    )

    private lateinit var interactor: UserPostsInteractor

    private val mockPostsRepo = mock<PostsRepo>()
    private val mockUsersRepo = mock<UsersRepo>()

    @Before
    fun setup() {
        interactor = UserPostsInteractorImpl(mockPostsRepo, mockUsersRepo)
    }

    @Test
    fun testFetchUserPosts() {
        whenever(mockPostsRepo.posts()).thenReturn(Single.just(listOf(testPost1, testPost2)))
        whenever(mockUsersRepo.users()).thenReturn(Single.just(listOf(testUser1, testUser2)))

        interactor.userPosts()
            .test()
            .assertValue {
                testUserPost1 == it[0] && testUserPost2 == it[1]
            }
    }

    @Test
    fun testFetchUserPostsRequestFails() {
        whenever(mockPostsRepo.posts()).thenReturn(Single.error(RuntimeException("TEST")))
        whenever(mockUsersRepo.users()).thenReturn(Single.just(listOf(testUser1, testUser2)))

        interactor.userPosts()
            .test()
            .assertError {
                it is java.lang.RuntimeException
            }
    }

    @Test
    fun testFetchUserPostsUserNotFound() {
        whenever(mockPostsRepo.posts()).thenReturn(Single.just(listOf(testPost1, testPost2)))
        whenever(mockUsersRepo.users()).thenReturn(Single.just(listOf(testUser1)))

        interactor.userPosts().test().assertError {
            it is ResourceNotFoundException
        }
    }
}