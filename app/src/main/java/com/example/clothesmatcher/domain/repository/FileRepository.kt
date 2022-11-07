package com.example.clothesmatcher.domain.repository

import com.example.clothesmatcher.data.model.Post
import retrofit2.Response

interface FileRepository {

    suspend fun uploadFile(photo: String): Response<Int>

}