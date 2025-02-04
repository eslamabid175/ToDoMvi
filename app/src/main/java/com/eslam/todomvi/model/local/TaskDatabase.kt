package com.eslam.todomvi.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    companion object{
        fun getInstance(context:Context) =
            Room.databaseBuilder(context,TaskDatabase::class.java,"todo").build()
    }
    abstract fun getTaskDao():TaskDao
}