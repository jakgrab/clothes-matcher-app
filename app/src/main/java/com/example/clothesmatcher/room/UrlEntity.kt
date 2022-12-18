package com.example.clothesmatcher.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url_tbl")
data class UrlEntity(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String
)
