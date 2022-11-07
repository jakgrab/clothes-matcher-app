package com.example.clothesmatcher.screens.main.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.domain.repository.FileRepository
import com.example.clothesmatcher.utils.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.example.clothesmatcher.utils.createTempFileFromUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import java.text.SimpleDateFormat

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: FileRepository
) : ViewModel() {

    val responseState = MutableStateFlow<Response<Int>?>(null)


    fun postImage(context: Context, uri: Uri?): Boolean {

        if (uri == null) {
            Log.e("PIC", "URI is null")
            return false
        }
        // uri to image to string
        // doesn't work, works for files, not images!
        //val image = uri.path?.let { File(it) }
        val tempFileName = SimpleDateFormat.getDateInstance().format(System.currentTimeMillis()) + ".jpg"


        try {
            val file = createTempFileFromUri(context, uri, tempFileName)
            Log.d("PIC", "Created file: ${file.toString()}")

            val imageToString = file!!.toBase64()

            viewModelScope.launch {
                val response =  repository.uploadFile(photo = imageToString!!)
                this@MainViewModel.responseState.value = response
                Log.d("PIC", "Upload successful: ${this@MainViewModel.responseState.value.toString()}")
            }
        } catch (e: IOException) {
            Log.d("PIC", "Failed to convert, error: ${e.printStackTrace()}")
        }
        return true


    }

}