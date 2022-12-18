package com.example.clothesmatcher.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
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

fun createTempFileFromUri(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file = File.createTempFile(fileName, "", context.cacheDir)
        org.apache.commons.io.FileUtils.copyInputStreamToFile(stream, file)
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun getTempFileName(): String {
    val dateFormat = SimpleDateFormat("dd-MM-yy-HH-mm", Locale.GERMAN)
    return dateFormat.format(System.currentTimeMillis()) + ".jpg"
}

fun decodeImageFromBase64(imageString: String?): Bitmap? {
    val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}
