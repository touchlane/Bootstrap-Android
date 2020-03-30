package com.touchlane.android.bootstrap

import androidx.appcompat.app.AppCompatActivity

inline fun <reified T> AppCompatActivity.extra(key: String, default: T): Lazy<T> = lazy {
    intent.extras
        ?.get(key)
        ?.let { it as T }
        ?: default
}

inline fun <reified T> AppCompatActivity.extra(key: String): Lazy<T> = lazy {
    intent.extras
        ?.get(key)
        .let { it as T }
}