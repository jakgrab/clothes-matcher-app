package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.data.model.ApiResponse
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.repository.ClothesRepository
import okhttp3.RequestBody
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val api: ClothesApi
) : ClothesRepository {

    // TODO changed arguments

    override suspend fun uploadFile(
        url: String,
        requestBody: RequestBody
    ): Response<ApiResponse>? {
        return try {
            api.uploadImage(url, requestBody)
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            null
        }
    }
}