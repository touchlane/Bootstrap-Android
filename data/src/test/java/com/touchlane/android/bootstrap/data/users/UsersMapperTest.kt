package com.touchlane.android.bootstrap.data.users

import com.touchlane.android.bootstrap.domain.users.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersMapperTest {

    private val testUserDto1 = UserDto(
        1,
        "name1",
        "username1",
        "email1",
        "phone1",
        "website1",
        Address(
            "street1",
            "city1",
            "suite1",
            "zipcode1",
            Geo(
                "1.0",
                "1.0"
            )
        ),
        Company(
            "name1",
            "catchPhrase1",
            "bs1"
        )
    )

    private val testUserDto2 = UserDto(
        2,
        "name2",
        "username2",
        "email2",
        "phone2",
        "website2",
        Address(
            "street2",
            "city2",
            "suite2",
            "zipcode2",
            Geo(
                "2.0",
                "2.0"
            )
        ),
        Company(
            "name2",
            "catchPhrase2",
            "bs2"
        )
    )
    private val testInput = listOf(testUserDto1, testUserDto2)
    private val testUser1 = User(
        1,
        "name1",
        "username1",
        "email1",
        "phone1"
    )
    private val testUser2 = User(
        2,
        "name2",
        "username2",
        "email2",
        "phone2"
    )

    private val mapper = UsersMapper()

    @Test
    fun testMap() {
        val output = mapper.map(testInput)

        assertEquals(testUser1, output[0])
        assertEquals(testUser2, output[1])
    }
}