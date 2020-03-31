package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.domain.comments.Comment
import org.junit.Assert.assertEquals
import org.junit.Test

class CommentsMapperTest {

    private val testCommentDto1 = CommentDto(
        1, 1, "name1", "email1", "body1"
    )
    private val testCommentDto2 = CommentDto(
        2, 2, "name2", "email2", "body2"
    )
    private val testInput = listOf(testCommentDto1, testCommentDto2)
    private val testComment1 = Comment(1, 1, "name1", "body1")
    private val testComment2 = Comment(2, 2, "name2", "body2")

    private val mapper = CommentsMapper()

    @Test
    fun testMap() {
        val output = mapper.map(testInput)

        assertEquals(testComment1, output[0])
        assertEquals(testComment2, output[1])
    }
}