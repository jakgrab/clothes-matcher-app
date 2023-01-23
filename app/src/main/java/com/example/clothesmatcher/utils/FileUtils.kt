package com.example.clothesmatcher.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class FileUtils {
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

    fun convertFileToBitmapAndResize(imageFile: File): Bitmap? {
        return try {
            val bmOptions = BitmapFactory.Options()
            var bitmap = BitmapFactory.decodeFile(imageFile.absolutePath, bmOptions)
            bitmap = Bitmap.createScaledBitmap(bitmap!!, 200, 200, true)
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun convertBitmapToBase64(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}