package com.example.clothesmatcher.domain.repository

import com.example.clothesmatcher.data.model.ApiResponse
import com.example.clothesmatcher.data.model.Post
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

interface FileRepository {

    suspend fun uploadFile(requestBody: RequestBody): Response<ApiResponse>

}