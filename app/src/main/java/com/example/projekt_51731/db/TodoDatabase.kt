package com.example.projekt_51731.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.projekt_51731.model.Todo
import com.example.projekt_51731.db.TodoDatabase


@Database(entities = [Todo::class], version=1)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {

    companion object{
        const val NAME = "Todo_DB"
    }

    abstract fun getTodoDao() : TodoDao

}