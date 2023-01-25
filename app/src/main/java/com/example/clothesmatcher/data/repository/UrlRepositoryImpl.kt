package com.example.clothesmatcher.data.repository

import com.example.clothesmatcher.repository.UrlRepository
import com.example.clothesmatcher.room.ServerDAO
import com.example.clothesmatcher.room.UrlEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UrlRepositoryImpl @Inject constructor(private val serverDAO: ServerDAO) : UrlRepository {

    override fun getAllUrls(): Flow<List<UrlEntity>> = serverDAO.getAllUrls()

    override suspend fun addUrl(url: UrlEntity) = serverDAO.addUrl(url)

    override suspend fun deleteUrl(url: UrlEntity) = serverDAO.deleteUrl(url)

    override fun getDefault() = serverDAO.getDefault()

    override suspend fun updateUrl(url: UrlEntity) = serverDAO.updateUrl(url)

    override suspend fun setEveryUrlAsNotDefault() = serverDAO.setEveryUrlAsNotDefault()
}