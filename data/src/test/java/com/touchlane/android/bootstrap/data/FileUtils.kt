package com.touchlane.android.bootstrap.data

import java.io.File
import java.io.IOException
import java.net.URL
import java.nio.file.Files

object FileUtils {

    fun fileAsString(path: String): String {
        val url: URL = javaClass.classLoader?.getResource(path)
            ?: throw IOException("Resources path not found")
        val file = File(url.path)
        val bytes: ByteArray = Files.readAllBytes(file.toPath())
        return String(bytes)
    }
}
