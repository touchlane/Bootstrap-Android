package com.touchlane.android.bootstrap.data.comments

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

class ApiCommentsDataSourceMockWebTest {

    private val testUrl = "/"
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

        dataSource = ApiCommentsDataSource(mockWebApi)
    }

    @Test
    fun testFetchComments() {
        val commentsJson = FileUtils.fileAsString("json/comments.json")
        mockWebServer.enqueue(MockResponse().setBody(commentsJson))

        val result = dataSource.commentsToPost(testPostId).blockingGet()

        assertEquals(testComment1, result[0])
        assertEquals(testComment2, result[1])
    }

    @Test(expected = HttpException::class)
    fun testFetchCommentsFailed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        dataSource.commentsToPost(2).blockingGet()
    }
}