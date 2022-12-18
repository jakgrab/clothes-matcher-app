package com.example.clothesmatcher.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UrlEntity::class], version = 1, exportSchema = false)
abstract class ServerDatabase : RoomDatabase() {
    abstract fun serverDao(): ServerDAO
}