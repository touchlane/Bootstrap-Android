package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.data.FileUtils
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApiCommentsDataSourceMockWebTest {

    private val testUrl = "https://api.com/comments?postId="
    private val testPostId = 1

    private val testComment1 = CommentDto(
        1,
        1,
        "id labore ex et quam laborum",
        "Eliseo@gardner.biz",
        "laudantium enim quasi est quidem magnam voluptate ipsam eos\n" +
                "tempora quo necessitatibus\n" +
                "dolor quam autem quasi\n" +
                "reiciendis et nam sapiente accusantium"
    )
    private val testComment2 = CommentDto(
        1,
        2,
        "quo vero reiciendis velit similique earum",
        "Jayne_Kuhic@sydney.com",
        "est natus enim nihil est dolore omnis voluptatem numquam\n" +
                "et omnis occaecati quod ullam at\n" +
                "voluptatem error expedita pariatur\n" +
                "nihil sint nostrum voluptatem reiciendis et"
    )

    private lateinit var dataSource: CommentsDataSource
    private lateinit var mockHttpClient: HttpClient

    @Before
    fun setup() {
        mockHttpClient = HttpClient(MockEngine) {

            install(JsonFeature) {
                serializer = GsonSerializer()
            }

            engine {
                addHandler { request ->
                    when (request.url.toString()) {
                        "$testUrl$testPostId" -> respond(
                            content = FileUtils.fileAsString("json/comments.json"),
                            headers = Headers.build {
                                append("content-type", "application/json; charset=utf-8")
                            }
                        )
                        else -> respondError(HttpStatusCode.NotFound)
                    }
                }
            }
        }

        dataSource = ApiCommentsDataSource(mockHttpClient, testUrl)
    }

    @Test
    fun testFetchComments() = runBlocking {
        val comments = dataSource.commentsToPost(testPostId)

        assertEquals(testComment1, comments[0])
        assertEquals(testComment2, comments[1])
    }

    @Test(expected = ClientRequestException::class)
    fun testFetchCommentsFailed() = runBlocking {
        dataSource.commentsToPost(2)
        Unit
    }
}