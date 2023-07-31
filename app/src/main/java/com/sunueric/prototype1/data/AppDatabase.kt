package com.sunueric.prototype1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TopicEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
}