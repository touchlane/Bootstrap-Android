package com.touchlane.android.bootstrap.data

import android.content.Context

interface Strings {

    operator fun get(id: Int): String

    operator fun get(id: Int, vararg formatArgs: Any): String
}

class StringsImpl(
    private val context: Context
) : Strings {

    override fun get(id: Int) = context.getString(id)

    override fun get(id: Int, vararg formatArgs: Any): String {
        return context.getString(id, *formatArgs)
    }
}