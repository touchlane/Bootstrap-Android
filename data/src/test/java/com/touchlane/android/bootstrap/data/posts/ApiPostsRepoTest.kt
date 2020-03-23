package com.touchlane.android.bootstrap.data.posts

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ApiPostsRepoTest {

    private lateinit var repo: PostsRepo
    private val mockDataSource = mock<PostsDataSource>()
    private val mockMapper = mock<Mapper<List<PostDto>, List<Post>>>()
    private val mockPostDtos = mock<List<PostDto>>()
    private val mockPosts = mock<List<Post>>()

    @Before
    fun setup() {
        repo = ApiPostsRepo(mockDataSource, mockMapper)
    }

    @Test
    fun testPosts() = runBlocking {
        whenever(mockDataSource.posts()).thenReturn(mockPostDtos)
        whenever(mockMapper.map(mockPostDtos)).thenReturn(mockPosts)

        assertEquals(mockPosts, repo.posts())

        verify(mockDataSource).posts()
        verify(mockMapper).map(mockPostDtos)
        Unit
    }
}