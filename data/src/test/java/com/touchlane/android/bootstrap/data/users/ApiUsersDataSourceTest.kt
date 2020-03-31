package com.touchlane.android.bootstrap.data.users

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

class ApiUsersDataSourceTest {

    private val testUrl = "/"

    private val testUser1 = UserDto(
        1,
        "Leanne Graham",
        "Bret",
        "Sincere@april.biz",
        "1-770-736-8031 x56442",
        "hildegard.org",
        Address(
            "Kulas Light",
            "Gwenborough",
            "Apt. 556",
            "92998-3874",
            Geo(
                "-37.3159",
                "81.1496"
            )
        ),
        Company(
            "Romaguera-Crona",
            "Multi-layered client-server neural-net",
            "harness real-time e-markets"
        )
    )
    private val testUser2 = UserDto(
        2,
        "Ervin Howell",
        "Antonette",
        "Shanna@melissa.tv",
        "010-692-6593 x09125",
        "anastasia.net",
        Address(
            "Victor Plains",
            "Wisokyburgh",
            "Suite 879",
            "90566-7771",
            Geo(
                "-43.9509",
                "-34.4618"
            )
        ),
        Company(
            "Deckow-Crist",
            "Proactive didactic contingency",
            "synergize scalable supply-chains"
        )
    )

    private lateinit var dataSource: UsersDataSource
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

        dataSource = ApiUsersDataSource(mockWebApi)
    }

    @Test
    fun testFetchUsers() {
        val usersJson = FileUtils.fileAsString("json/users.json")
        mockWebServer.enqueue(MockResponse().setBody(usersJson))

        val users = dataSource.users().blockingGet()

        assertEquals(testUser1, users[0])
        assertEquals(testUser2, users[1])
    }

    @Test(expected = HttpException::class)
    fun testFetchUsersFailed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        dataSource.users().blockingGet()
    }
}