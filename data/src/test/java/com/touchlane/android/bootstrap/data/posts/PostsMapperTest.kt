package com.touchlane.android.bootstrap.data.posts

import com.touchlane.android.bootstrap.domain.posts.Post
import org.junit.Assert.assertEquals
import org.junit.Test

class PostsMapperTest {

    private val testPostDto1 = PostDto(1, 1, "title1", "body1")
    private val testPostDto2 = PostDto(2, 2, "title2", "body2")
    private val testInput = listOf(testPostDto1, testPostDto2)
    private val testPost1 = Post(1, 1, "title1", "body1")
    private val testPost2 = Post(2, 2, "title2", "body2")

    private val mapper = PostsMapper()

    @Test
    fun testMap() {
        val output = mapper.map(testInput)

        assertEquals(testPost1, output[0])
        assertEquals(testPost2, output[1])
    }
}