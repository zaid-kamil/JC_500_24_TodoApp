package com.digi.jc_500_24_todoapp.screens

import androidx.lifecycle.ViewModel
import com.digi.jc_500_24_todoapp.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    // uiState
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    // event handler
    fun onEvent(event: TaskEvent) {
        when (event) {
            TaskEvent.AddTask -> addTask()
            is TaskEvent.UpdateTask -> {
                updateTask(event.name, event.isComplete)
            }
            is TaskEvent.EditTaskImportance -> {
                _uiState.update {
                    it.copy(isTaskImportant = event.isImportant)
                }
            }
            is TaskEvent.EditTaskName -> {
                _uiState.update {
                    it.copy(editTaskMsg = event.name)
                }
            }
            TaskEvent.ToggleSheet -> {
                _uiState.update {
                    it.copy(isSheetOpen = !it.isSheetOpen)
                }
            }
        }
    }

    private fun updateTask(name: String, complete: Boolean) {
        _uiState.value.tasks.forEachIndexed { index, task ->
            if (task.name == name) {
                val updatedTasks = _uiState.value.tasks.toMutableList()
                updatedTasks[index] = task.copy(isCompleted = complete)
                _uiState.value = _uiState.value.copy(tasks = updatedTasks)
            }
        }
    }

    private fun addTask() {
        val updatedTasks = _uiState.value.tasks.toMutableList()
        updatedTasks.add(
            Task(
                name = _uiState.value.editTaskMsg,
                isImportant = _uiState.value.isTaskImportant
            )
        )
        _uiState.value = _uiState.value.copy(tasks = updatedTasks)
    }

}