package com.example.clothesmatcher.repository

import com.example.clothesmatcher.data.model.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response

interface ClothesRepository {
    suspend fun uploadFile(requestBody: RequestBody): Response<ApiResponse>?
}