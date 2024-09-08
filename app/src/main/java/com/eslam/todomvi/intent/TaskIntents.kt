package com.eslam.todomvi.intent

import com.eslam.todomvi.model.local.Task

sealed class TaskIntents {
data class Insert(val task: Task):TaskIntents()
data class update(val task: Task):TaskIntents()
data class delet(val task: Task):TaskIntents()
//لو عاوز اضيف اي نية
// object getAllTasks:TaskIntents()
}