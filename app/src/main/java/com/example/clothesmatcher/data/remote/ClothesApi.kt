package com.example.clothesmatcher.data.remote

import com.example.clothesmatcher.data.model.ImageResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClothesApi {
    // TODO response class
    @Headers("Content-Type: application/json")
    @POST("./")
    suspend fun uploadImage(
        // @Body photo: String b4
        @Body requestBody: RequestBody
    ): Response<ImageResponse>

//    companion object {
//        val instance: FileApi by lazy {
//            Retrofit.Builder()
//                .baseUrl("https://my-json-server.typicode.com/typicode/demo/")
//                .build()
//                .create(FileApi::class.java)
//        }
//    }
}