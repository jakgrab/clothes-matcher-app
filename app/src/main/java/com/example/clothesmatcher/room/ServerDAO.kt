package com.example.clothesmatcher.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDAO {

    @Query("SELECT * from url_tbl")
    fun getAllUrls(): Flow<List<UrlEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun addUrl(url: UrlEntity)

    @Delete
    suspend fun deleteUrl(url: UrlEntity)

    @Query("SELECT url FROM url_tbl WHERE `default`=1")
    fun getDefault(): String

    @Update
    suspend fun updateUrl(url: UrlEntity)

    @Query("UPDATE url_tbl SET `default` = 0")
    suspend fun setEveryUrlAsNotDefault()

}