package com.touchlane.android.bootstrap.data.users

import com.google.gson.annotations.SerializedName
import com.touchlane.android.bootstrap.domain.users.User

data class UserDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("company")
    val company: Company
)

fun UserDto.toUser(): User = User(
    id, name, username, email, phone
)

data class Address(
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipCode: String,
    @SerializedName("geo")
    val geo: Geo
)

data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("long")
    val long: String
)

data class Company(
    @SerializedName("name")
    val name: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("bs")
    val bs: String
)