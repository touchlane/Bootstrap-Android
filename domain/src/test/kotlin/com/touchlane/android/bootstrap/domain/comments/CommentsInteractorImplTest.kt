package com.touchlane.android.bootstrap.domain.comments

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
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
    fun testFetchCommentsForPost() {
        whenever(mockCommentsRepo.commentsForPost(testCommentId)).thenReturn(
            Single.just(
                testComments
            )
        )

        interactor.commentsToPost(testCommentId)
            .test()
            .assertValue(testComments)
    }

    @Test
    fun testFetchCommentsForPostFailed() {
        whenever(mockCommentsRepo.commentsForPost(testCommentId)).thenReturn(
            Single.error(
                RuntimeException("TEST")
            )
        )

        interactor.commentsToPost(testCommentId)
            .test()
            .assertError { it is java.lang.RuntimeException }
    }
}