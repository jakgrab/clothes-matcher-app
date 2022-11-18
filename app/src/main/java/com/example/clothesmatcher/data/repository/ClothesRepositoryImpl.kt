package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.data.model.ImageResponse
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.domain.repository.ClothesRepository
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val api: ClothesApi
) : ClothesRepository {
    // TODO response class
    override suspend fun uploadFile(requestBody: RequestBody): Response<ImageResponse> {
        return api.uploadImage(requestBody)
    }

//    override suspend fun uploadFile( photo: String): Response<ApiResponse> {
//        return api.uploadImage(photo)
//    }
}