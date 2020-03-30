package com.touchlane.android.bootstrap.domain

sealed class Result<out T> {

    data class Success<out T>(val value: T) : Result<T>()

    data class Error(val throwable: Throwable) : Result<Nothing>()
}