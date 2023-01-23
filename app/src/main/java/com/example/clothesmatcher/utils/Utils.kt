package com.example.clothesmatcher.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import kotlinx.coroutines.NonDisposableHandle.parent
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

fun File.toBase64(): String? {
    val result: String?
    inputStream().use { inputStream ->
        val sourceBytes = inputStream.readBytes()
        result = Base64.encodeToString(sourceBytes, Base64.DEFAULT)
    }
    return result
}

fun matchIpOrUrl(input: String): Boolean {
    // Regex for matching IP addresses
    val ipRegex = """\b^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\.(?!$)|$)){4}$\b""".toRegex()

    // Modified regex for matching URLs
    val urlRegex =
        """\bhttps?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)\b""".toRegex()

    return input.matches(ipRegex) || input.matches(urlRegex)
}

