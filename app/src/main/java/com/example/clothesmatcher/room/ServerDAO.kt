package com.example.clothesmatcher.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDAO {

    @Query("SELECT * from url_tbl")
    fun getAllUrls(): Flow<List<UrlEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun addUrl(url: UrlEntity)

    @Delete
    suspend fun deleteUrl(url: UrlEntity)
}