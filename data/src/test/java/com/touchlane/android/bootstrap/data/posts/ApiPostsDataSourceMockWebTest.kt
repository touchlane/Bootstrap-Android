package com.touchlane.android.bootstrap.data.posts

import com.google.gson.GsonBuilder
import com.touchlane.android.bootstrap.data.FileUtils
import com.touchlane.android.bootstrap.data.api.WebApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiPostsDataSourceMockWebTest {

    private val testUrl = "/"

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
    private lateinit var mockWebApi: WebApi

    @get:Rule
    var mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebApi = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .baseUrl(mockWebServer.url(testUrl).toString())
            .build()
            .create(WebApi::class.java)

        dataSource = ApiPostsDataSource(mockWebApi)
    }

    @Test
    fun testFetchPosts() {
        val postsJson = FileUtils.fileAsString("json/posts.json")
        mockWebServer.enqueue(MockResponse().setBody(postsJson))

        val posts = dataSource.posts().blockingGet()

        assertEquals(testPost1, posts[0])
        assertEquals(testPost2, posts[1])
    }

    @Test(expected = HttpException::class)
    fun testFetchPoststFailed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        dataSource.posts().blockingGet()
    }
}