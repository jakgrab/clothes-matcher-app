package com.example.clothesmatcher.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url_tbl")
data class UrlEntity(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "default")
    var isDefault: Int = 0
    // maybe add Int parameter "default" which can be set to 1 or 0
)
