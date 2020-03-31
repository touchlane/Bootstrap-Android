package com.touchlane.android.bootstrap.data.comments

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo
import io.reactivex.rxjava3.core.Single
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
    fun testComments() {
        whenever(mockDataSorce.commentsToPost(1)).thenReturn(Single.just(mockCommentDtos))
        whenever(mockMapper.map(mockCommentDtos)).thenReturn(mockComments)

        repo.commentsForPost(1)
            .test()
            .assertValue(mockComments)

        verify(mockDataSorce).commentsToPost(1)
        verify(mockMapper).map(mockCommentDtos)
    }
}