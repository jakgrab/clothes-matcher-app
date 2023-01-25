package com.example.clothesmatcher.data.remote

import com.example.clothesmatcher.data.model.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface ClothesApi {
    @Headers("Accept: application/json")
    //@POST("./")
    @POST("./")
    suspend fun uploadImage(
        @Body requestBody: RequestBody
    ): Response<ApiResponse>
}