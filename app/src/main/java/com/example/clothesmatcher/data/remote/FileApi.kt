package com.example.clothesmatcher.data.remote

import com.example.clothesmatcher.data.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface FileApi {

    @Headers("Content-Type: application/json")
    @POST("./")
    suspend fun uploadImage(
        @Body photo: String
    ): Response<Int>

//    companion object {
//        val instance: FileApi by lazy {
//            Retrofit.Builder()
//                .baseUrl("https://my-json-server.typicode.com/typicode/demo/")
//                .build()
//                .create(FileApi::class.java)
//        }
//    }
}