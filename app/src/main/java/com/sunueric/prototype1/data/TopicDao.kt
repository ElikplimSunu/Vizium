package com.sunueric.prototype1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopicDao {
    @Insert
    suspend fun insert(topic: TopicEntity)

    @Query("SELECT * FROM topics")
    suspend fun getAllTopics(): List<TopicEntity>
}