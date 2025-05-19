package com.example.projekt_51731.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: String = "",
    val title: String = "",
    val description: String = "",
    val modifiedAt: Long = System.currentTimeMillis()
)
