package com.example.projekt_51731

import android.app.Application
import androidx.room.Room
import com.example.projekt_51731.db.TodoDatabase

class MainApplication : Application() {

    companion object{
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            toString()
        ).build()
    }

}