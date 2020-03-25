package com.touchlane.android.bootstrap.data.users

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

class ApiUsersDataSourceTest {

    private val testUrl = "https://api.com/users"

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
                            content = FileUtils.fileAsString("json/users.json"),
                            headers = Headers.build {
                                append("content-type", "application/json; charset=utf-8")
                            }
                        )
                        else -> respondError(HttpStatusCode.NotFound)
                    }
                }
            }
        }

        dataSource = ApiUsersDataSource(mockHttpClient, testUrl)
    }

    @Test
    fun testFetchUsers() = runBlocking {
        val users = dataSource.users()

        assertEquals(testUser1, users[0])
        assertEquals(testUser2, users[1])
    }

    @Test(expected = ClientRequestException::class)
    fun testFetchUsersFailed() = runBlocking {
        dataSource = ApiUsersDataSource(mockHttpClient, "wrong url")
        dataSource.users()
        Unit
    }
}