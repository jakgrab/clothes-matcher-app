package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.data.model.ApiResponse
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.repository.ClothesRepository
import okhttp3.RequestBody
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class ClothesRepositoryImpl(
    private val api: ClothesApi
) : ClothesRepository {

    override suspend fun uploadFile(
        requestBody: RequestBody
    ): Response<ApiResponse>? {
        return try {
            api.uploadImage(requestBody)
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            null
        }
    }
}