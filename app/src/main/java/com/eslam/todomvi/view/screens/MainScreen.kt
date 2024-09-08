package com.eslam.todomvi.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eslam.todomvi.intent.TaskIntents
import com.eslam.todomvi.model.local.Task




//lambda used to transform intint to view
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(list:List<Task>,onIntent:(TaskIntents)->Unit){
  val title = remember { mutableStateOf("") }
  val desc = remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Todo Tasks") })
    }) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {


if (list.isEmpty()){
    Box (modifier = Modifier
        .fillMaxSize(), contentAlignment = Alignment.Center){
Text(text = "NoThing Found")
    }
}
        else{
    TaskList(list = list, modifier = Modifier.padding(it),onIntent)
}
        MainscreenBottom(title,desc,onIntent)
    }}
}

@Composable
fun MainscreenBottom(
    title: MutableState<String>,
    desc: MutableState<String>,
    onIntent: (TaskIntents) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
TextField(value = title.value, onValueChange = {title.value=it}, label = { Text(text = "Enter Title")}, modifier = Modifier.fillMaxWidth() )
TextField(value = desc.value, onValueChange = {desc.value=it}, label = { Text(text = "Enter Desc")}, modifier = Modifier.fillMaxWidth() )
    Button(onClick = { onIntent.invoke(TaskIntents.Insert(Task(title = title.value, description = desc.value, isCompleted = false, id = 0)))
    title.value=""
        desc.value=""
    })
    {
        Text(text = "Add Task")
    }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(list: List<Task>, modifier: Modifier,onIntent:(TaskIntents)->Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(36.dp)) {
        items(list){
            val ischecked= remember {
                mutableStateOf(it.isCompleted)
            }
Column(modifier = Modifier.
combinedClickable(enabled = true,
    onClick = {},
    onLongClick ={
        onIntent.invoke(TaskIntents.delet(it))
    })
.fillMaxWidth()) {
Row (modifier = Modifier
    .padding(horizontal = 12.dp, vertical = 8.dp)
    .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically){
Text(text = it.title)
    Text(text = it.description)
    Checkbox(checked = ischecked.value, onCheckedChange ={Checked->
        ischecked.value=Checked
onIntent.invoke(TaskIntents.update(it.copy(isCompleted =Checked)))

    })
}
    HorizontalDivider()
}
        }
    }
}
