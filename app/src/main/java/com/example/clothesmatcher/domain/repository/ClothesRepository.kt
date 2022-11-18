package com.example.clothesmatcher.domain.repository

import com.example.clothesmatcher.data.model.ImageResponse
import okhttp3.RequestBody
import retrofit2.Response

interface ClothesRepository {
    // TODO response class
    suspend fun uploadFile(requestBody: RequestBody): Response<ImageResponse>

}