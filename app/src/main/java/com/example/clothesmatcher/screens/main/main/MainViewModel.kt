package com.example.clothesmatcher.screens.main.main

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.domain.repository.ClothesRepository
import com.example.clothesmatcher.utils.createTempFileFromUri
import com.example.clothesmatcher.utils.decodeImageFromBase64
import com.example.clothesmatcher.utils.getTempFileName
import com.example.clothesmatcher.utils.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ClothesRepository
) : ViewModel() {

    //private val responseState = MutableStateFlow<Response<ApiResponse>?>(null)
    private val _responseImageState = MutableStateFlow<Bitmap?>(null)
    val responseImageState = _responseImageState.asStateFlow()

    fun postImage(imageString: String?) {
        val jsonObject = JSONObject()
        jsonObject.put("photo", imageString)
        val jsonObjectToString = jsonObject.toString()

        val requestBody = jsonObjectToString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.uploadFile(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("PIC", "Response: ${response.body()}")
                    _responseImageState.value =
                        getImageFromResponse(response.body()?.returned_image)
                } else {
                    Log.e("PIC", "Retrofit error: ${response.code()}")
                }
            }
        }
    }

    fun imageStringFromUri(context: Context, uri: Uri?): String? {
        if (uri == null)
            return null

        val file = createTempFileFromUri(context, uri, getTempFileName())

        Log.d("PIC", "Created file: ${file.toString()}")

        val fileToString = file!!.toBase64()
        file.delete()

        return fileToString
    }

    private fun getImageFromResponse(responseImageString: String?): Bitmap? {
        return decodeImageFromBase64(responseImageString)
    }
}