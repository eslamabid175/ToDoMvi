package com.eslam.todomvi.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean=false
)
