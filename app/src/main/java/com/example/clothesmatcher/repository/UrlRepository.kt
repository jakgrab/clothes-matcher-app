package com.example.clothesmatcher.repository


import com.example.clothesmatcher.room.UrlEntity
import kotlinx.coroutines.flow.Flow


interface UrlRepository{

    fun getAllUrls(): Flow<List<UrlEntity>>

    suspend fun addUrl(url: UrlEntity)

    suspend fun deleteUrl(url: UrlEntity)

    fun getDefault(): String?

    suspend fun updateUrl(url: UrlEntity)

    suspend fun setEveryUrlAsNotDefault()
}