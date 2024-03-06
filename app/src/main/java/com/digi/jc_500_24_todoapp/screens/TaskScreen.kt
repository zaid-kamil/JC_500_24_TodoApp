package com.digi.jc_500_24_todoapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digi.jc_500_24_todoapp.data.Task
import com.digi.jc_500_24_todoapp.ui.theme.JC_500_24_TodoAppTheme

@Composable
fun TaskScreen(
    uiState: TaskUiState = TaskUiState(),
    onEvent: (TaskEvent) -> Unit = {},
) {
    Scaffold(bottomBar = {
        TaskBottomBar(onEvent = onEvent)
    }) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.tasks) { task ->
                    TaskItem(task = task, onEvent = onEvent)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(task: Task, onEvent: (TaskEvent) -> Unit) {
    Card(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    task.name, Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )
                if (task.isImportant) {
                    Badge {
                        Text(
                            text = "Important",
                            modifier = Modifier.padding(2.dp),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
            Checkbox(checked = task.isCompleted, onCheckedChange = {
                onEvent(
                    TaskEvent.UpdateTask(
                        name = task.name,
                        isComplete = !task.isCompleted
                    )
                )
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    uiState: TaskUiState,
    onEvent: (TaskEvent) -> Unit,
) {
    ModalBottomSheet(onDismissRequest = {}) {
        BasicTextField(value = uiState.editTaskMsg, onValueChange = {})
        Checkbox(checked = uiState.isTaskImportant, onCheckedChange = {})
    }
}

@Composable
fun TaskBottomBar(onEvent: (TaskEvent) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // handle sheet visibility
        FloatingActionButton(onClick = { onEvent(TaskEvent.ToggleSheet) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add task")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskScreenPreview() {
    JC_500_24_TodoAppTheme {
        TaskScreen(
            uiState = TaskUiState(
                tasks = listOf(
                    Task("Do Laundry 🗑️", isImportant = true),
                    Task("Clean Room 🧹"),
                    Task("Buy Groceries ??????", isCompleted = true),
                    Task("Sleep 😴😪", isImportant = true),
                )
            )
        )
    }
}