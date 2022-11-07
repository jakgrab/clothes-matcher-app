package com.example.clothesmatcher.utils

import android.content.Context
import android.net.Uri
import android.util.Base64
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

fun File.toBase64(): String? {
    val result: String?
    inputStream().use { inputStream ->
        val sourceBytes = inputStream.readBytes()
        result = Base64.encodeToString(sourceBytes, Base64.DEFAULT)
    }
    return result
}

//private fun getBase64ForUriAndPossiblyCrash(context: Context, uri: Uri): String {
//    //val contentResolver = ContentResolver(context)
//    //context.contentResolver.openInputStream(uri).readBytes()
//    try {
//        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()
//
//        return Base64.encodeToString(bytes, Base64.DEFAULT)
//
//    } catch (error: IOException) {
//        error.printStackTrace() // This exception always occurs
//    }
//}
// encoding an image

//fun encode(imageUri: Uri): String {
//    val input =
//}
fun createTempFileFromUri(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file = File.createTempFile(fileName, "", context.cacheDir)
        org.apache.commons.io.FileUtils.copyInputStreamToFile(stream,file)
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}