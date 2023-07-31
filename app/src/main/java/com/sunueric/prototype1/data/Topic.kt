package com.sunueric.prototype1.data

import androidx.room.PrimaryKey

data class TopicEntity(
@PrimaryKey(autoGenerate = true)
val id: Long = 0,
val name: String,
val body: String
)