package com.touchlane.android.bootstrap.domain.comments

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.touchlane.android.bootstrap.domain.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CommentsInteractorImplTest {

    private lateinit var interactor: CommentsInteractor

    private val testCommentId = 2
    private val mockCommentsRepo = mock<CommentsRepo>()
    private val testComments = listOf(Comment(1, 1, "name", "body"))

    @Before
    fun setup() {
        interactor = CommentsInteractorImpl(mockCommentsRepo)
    }

    @Test
    fun testFetchCommentsForPost() = runBlocking {
        whenever(mockCommentsRepo.commentsForPost(testCommentId)).thenReturn(testComments)

        val result = interactor.commentsToPost(testCommentId)

        assertTrue(result is Result.Success)
        assertEquals(testComments, (result as Result.Success).value)
    }

    @Test
    fun testFetchCommentsForPostFailed() = runBlocking {
        whenever(mockCommentsRepo.commentsForPost(testCommentId)).thenThrow(RuntimeException("TEST"))

        val result = interactor.commentsToPost(testCommentId)

        assertTrue(result is Result.Error)
    }
}