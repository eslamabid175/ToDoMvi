package com.eslam.todomvi.model.di

import android.content.Context
import com.eslam.todomvi.model.local.TaskDao
import com.eslam.todomvi.model.local.TaskDatabase
import com.eslam.todomvi.model.repository.TodoRepository
import com.eslam.todomvi.model.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ModelModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return TaskDatabase.getInstance(context)
    }
    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase) : TaskDao {
        return taskDatabase.getTaskDao()

    }

    @Provides
    fun provideTaskRepository(taskDao: TaskDao):TodoRepository{
        return TodoRepositoryImpl(taskDao)
    }
}