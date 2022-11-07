package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.data.model.Post
import com.example.clothesmatcher.data.remote.FileApi
import com.example.clothesmatcher.domain.repository.FileRepository
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val api: FileApi
) : FileRepository {
//    override suspend fun uploadFile(post: Post): Boolean {
//        return try {
//            api.uploadImage(post)
//            true
//        } catch (e: IOException) {
//            e.printStackTrace()
//            false
//        } catch (e: HttpException) {
//            e.printStackTrace()
//            false
//        }
//    }

    override suspend fun uploadFile( photo: String): Response<Int> {
        return api.uploadImage(photo)
    }
}