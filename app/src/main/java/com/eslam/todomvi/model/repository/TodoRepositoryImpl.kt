package com.eslam.todomvi.model.repository

import com.eslam.todomvi.model.local.Task
import com.eslam.todomvi.model.local.TaskDao
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val taskDao: TaskDao): TodoRepository  {
    override fun getAllTasks(): Flow<List<Task>> =taskDao.getAllTasks()

    override suspend fun addTask(task: Task) {
taskDao.insert(task)
    }

    override suspend fun updateTask(task: Task) {
taskDao.update(task)    }

    override suspend fun deleteTask(task: Task) {
taskDao.delete(task)
    }
}