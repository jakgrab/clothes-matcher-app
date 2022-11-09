package com.example.clothesmatcher.screens.main.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.data.model.ApiResponse
import com.example.clothesmatcher.domain.repository.FileRepository
import com.example.clothesmatcher.utils.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.example.clothesmatcher.utils.createTempFileFromUri
import com.example.clothesmatcher.utils.getTempFileName
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FileRepository
) : ViewModel() {

    //private val responseState = MutableStateFlow<Response<ApiResponse>?>(null)


    fun postImage(imageString: String?) {

        // uri to image to string
        // doesn't work, works for files, not images!
        //val image = uri.path?.let { File(it) }
//        SimpleDateFormat.getDateInstance(2, )
//        val dateFormat = SimpleDateFormat()
//        val tempFileName = SimpleDateFormat.getDateInstance().format(System.currentTimeMillis()) + ".jpg"
        val jsonObject = JSONObject()
        jsonObject.put("photo", imageString)
        val jsonObjectToString = jsonObject.toString()

        val requestBody = jsonObjectToString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.uploadFile(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("PIC", "Response: ${response.body()}")
                } else {
                    Log.e("PIC", "Retrofit error: ${response.code()}")
                }
            }
//                //this@MainViewModel.responseState.value = response
//                //Log.d("PIC","RESPONSE: ${response.body().toString()}")
//                Log.d("PIC", "-------------------------------------")
//                //Log.d("PIC","Upload successful: ${this@MainViewModel.responseState.value.toString()}")
//                Log.d("PIC", "Upload successful: $response")
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

}