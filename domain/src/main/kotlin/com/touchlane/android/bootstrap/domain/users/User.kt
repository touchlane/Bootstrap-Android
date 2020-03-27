package com.touchlane.android.bootstrap.domain.users

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
)