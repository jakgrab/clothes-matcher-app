package com.example.clothesmatcher.data.remote

import com.example.clothesmatcher.data.model.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface ClothesApi {
    //@Headers("Content-Type: application/json") ????
    @Headers("Accept: application/json")
    @POST("./")
    suspend fun uploadImage(
        @Url url: String,
        @Body requestBody: RequestBody
    ): Response<ApiResponse>


}