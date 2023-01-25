package com.practice.tasklist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    @ColumnInfo(name = "is_done")
    var isDone: Boolean = false
)