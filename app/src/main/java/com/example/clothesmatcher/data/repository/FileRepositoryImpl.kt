package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.data.model.ApiResponse
import com.example.clothesmatcher.data.model.Post
import com.example.clothesmatcher.data.remote.FileApi
import com.example.clothesmatcher.domain.repository.FileRepository
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val api: FileApi
) : FileRepository {

    override suspend fun uploadFile(requestBody: RequestBody): Response<ApiResponse> {
        return api.uploadImage(requestBody)
    }

//    override suspend fun uploadFile( photo: String): Response<ApiResponse> {
//        return api.uploadImage(photo)
//    }
}