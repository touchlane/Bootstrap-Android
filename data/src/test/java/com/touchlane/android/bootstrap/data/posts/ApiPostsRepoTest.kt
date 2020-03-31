package com.touchlane.android.bootstrap.data.posts

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import io.reactivex.rxjava3.core.Single
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
    fun testPosts() {
        whenever(mockDataSource.posts()).thenReturn(Single.just(mockPostDtos))
        whenever(mockMapper.map(mockPostDtos)).thenReturn(mockPosts)

        repo.posts()
            .test()
            .assertValue(mockPosts)

        verify(mockDataSource).posts()
        verify(mockMapper).map(mockPostDtos)
    }
}