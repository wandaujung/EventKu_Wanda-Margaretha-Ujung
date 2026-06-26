package com.wanda0002.eventku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String,
    val date: String,
    val location: String,

    val imageUri: String = "",

    val isSynced: Boolean = false,

    val isDeleted: Boolean = false,

    val source: String = "LOCAL"
)