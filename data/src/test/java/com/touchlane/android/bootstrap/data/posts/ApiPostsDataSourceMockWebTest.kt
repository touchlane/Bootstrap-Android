package com.touchlane.android.bootstrap.data.posts

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

class ApiPostsDataSourceMockWebTest {

    private val testUrl = "https://api.com/posts"

    private val testPost1 = PostDto(
        1,
        1,
        "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto"
    )
    private val testPost2 = PostDto(
        2,
        1,
        "qui est esse",
        "est rerum tempore vitae\n" +
                "sequi sint nihil reprehenderit dolor beatae ea dolores neque\n" +
                "fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\n" +
                "qui aperiam non debitis possimus qui neque nisi nulla"
    )

    private lateinit var dataSource: PostsDataSource
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
                        testUrl -> respond(
                            content = FileUtils.fileAsString("json/posts.json"),
                            headers = Headers.build {
                                append("content-type", "application/json; charset=utf-8")
                            }
                        )
                        else -> respondError(HttpStatusCode.NotFound)
                    }
                }
            }
        }

        dataSource = ApiPostsDataSource(mockHttpClient, testUrl)
    }

    @Test
    fun testFetchPosts() = runBlocking {
        val posts = dataSource.posts()

        assertEquals(testPost1, posts[0])
        assertEquals(testPost2, posts[1])
    }

    @Test(expected = ClientRequestException::class)
    fun testFetchPoststFailed() = runBlocking {
        dataSource = ApiPostsDataSource(mockHttpClient, "wrong url")
        dataSource.posts()
        Unit
    }
}