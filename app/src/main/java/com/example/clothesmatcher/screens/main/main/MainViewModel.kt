package com.example.clothesmatcher.screens.main.main

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.data.remote.RetrofitClient
import com.example.clothesmatcher.data.repository.ClothesRepositoryImpl
import com.example.clothesmatcher.utils.FileUtils
import com.example.clothesmatcher.utils.toBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainViewModel : ViewModel() {

    private val _responseImageState = MutableStateFlow<List<Bitmap?>>(emptyList())
    val responseImageState = _responseImageState.asStateFlow()

    private val _isImageListEmpty = MutableStateFlow(false)
    val isImageListEmpty = _isImageListEmpty.asStateFlow()

    private val _isConnectionSuccessful = MutableStateFlow<Boolean?>(null)
    val isConnectionSuccessful = _isConnectionSuccessful.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun postImage(imageString: String?, defaultUrl: String, numResults: Int) {
        val jsonObject = JSONObject()

        jsonObject.put("numResults", numResults)
        jsonObject.put("photo", imageString)

        val jsonObjectToString = jsonObject.toString()
        val requestBody = jsonObjectToString.toRequestBody("application/json".toMediaTypeOrNull())

        Log.d("URL", "POST IMAGE TO: $defaultUrl")
        Log.d("URL", "NUM RESULTS: $numResults")

        val client = createRetrofitClient(defaultUrl)
        val repository = ClothesRepositoryImpl(client)

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.uploadFile(requestBody)

            if (response == null) {
                _isConnectionSuccessful.value = false
                Log.d("tag", "ViewModel _isConnectionSuccessful: ${_isConnectionSuccessful.value}")
                return@launch
            }

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    _isConnectionSuccessful.value = true
                    Log.d("PIC", "Response: ${response.body()}")

                    val listOfImages =
                        response.body()?.images?.map { getImageFromResponse(it.image) }

                    Log.d("pic", "LIst of images: $listOfImages")

                    if (!listOfImages.isNullOrEmpty()) {
                        _responseImageState.value = listOfImages
                    } else {
                        _isImageListEmpty.value = true
                    }
                } else {
                    Log.e("PIC", "Retrofit error: ${response.errorBody()}")
                    _errorMessage.value = response.errorBody().toString()
                    _isConnectionSuccessful.value = false
                }
            }
        }
    }
    private fun createRetrofitClient(baseUrl: String): ClothesApi {
        return RetrofitClient().createFileApi(baseUrl)
    }

    fun imageStringFromUri(context: Context, uri: Uri?): String? {
        val fileUtils = FileUtils()
        if (uri == null)
            return null

        val file = fileUtils.createTempFileFromUri(context, uri, fileUtils.getTempFileName())

        Log.d("PIC", "Created file: ${file.toString()}")

        val fileToString = file!!.toBase64()
        file.delete()

        return fileToString
    }

    fun convertUriToBase64(context: Context, uri: Uri?): String? {
        val fileUtils = FileUtils()
        if (uri == null)
            return null
        val file = fileUtils.createTempFileFromUri(context, uri, fileUtils.getTempFileName())
        val fileToBitmap = fileUtils.convertFileToBitmapAndResize(file)

        file?.delete()

        return fileUtils.convertBitmapToBase64(fileToBitmap)
    }

    private fun getImageFromResponse(responseImageString: String?): Bitmap? {
        val fileUtils = FileUtils()
        return fileUtils.decodeImageFromBase64(responseImageString)
    }
}