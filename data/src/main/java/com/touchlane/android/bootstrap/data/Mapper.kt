package com.touchlane.android.bootstrap.data

interface Mapper<in I, out O> {

    fun map(input: I): O
}