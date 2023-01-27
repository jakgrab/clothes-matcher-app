package com.example.clothesmatcher.utils

import android.util.Base64
import java.io.File

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



    // Matches ports too
    val urlOrIpRegex = """^((https?:\/\/)|(www.))(?:([a-zA-Z]+)|(\d+\.\d+.\d+.\d+))(:\d{4})?${'$'}""".toRegex()
    //return input.matches(ipRegex) || input.matches(urlRegex)
    return input.matches(urlOrIpRegex)
}

