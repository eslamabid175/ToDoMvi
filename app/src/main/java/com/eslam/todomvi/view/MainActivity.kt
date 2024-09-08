package com.eslam.todomvi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eslam.todomvi.intent.TaskIntents
import com.eslam.todomvi.model.repository.TodoRepository
import com.eslam.todomvi.view.screens.MainScreen
import com.eslam.todomvi.view.ui.theme.ToDoMviTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
   lateinit var repository: TodoRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoMviTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
Surface(modifier = Modifier.padding(innerPadding)) {
    val mlist by repository.getAllTasks().collectAsState(initial = emptyList())
    val scope =rememberCoroutineScope()
MainScreen(list = mlist) {intent ->
    when(intent){
        is TaskIntents.Insert ->scope.launch(Dispatchers.IO) {repository.addTask(task = intent.task)        }

        is TaskIntents.delet ->  scope.launch(Dispatchers.IO) {repository.deleteTask(task = intent.task)        }
        is TaskIntents.update -> scope.launch(Dispatchers.IO) {repository.updateTask(task = intent.task)        }
    }

}
}
                }
            }
        }
    }
}

