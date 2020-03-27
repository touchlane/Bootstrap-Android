package com.touchlane.android.bootstrap.data.comments

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApiCommentsRepoTest {

    private lateinit var repo: CommentsRepo
    private val mockDataSorce = mock<CommentsDataSource>()
    private val mockMapper = mock<Mapper<List<CommentDto>, List<Comment>>>()
    private val mockCommentDtos = mock<List<CommentDto>>()
    private val mockComments = mock<List<Comment>>()

    @Before
    fun setup() {
        repo = ApiCommentsRepo(mockDataSorce, mockMapper)
    }

    @Test
    fun testComments() = runBlocking {
        whenever(mockDataSorce.commentsToPost(1)).thenReturn(mockCommentDtos)
        whenever(mockMapper.map(mockCommentDtos)).thenReturn(mockComments)

        assertEquals(mockComments, repo.commentsForPost(1))

        verify(mockDataSorce).commentsToPost(1)
        verify(mockMapper).map(mockCommentDtos)
        Unit
    }
}