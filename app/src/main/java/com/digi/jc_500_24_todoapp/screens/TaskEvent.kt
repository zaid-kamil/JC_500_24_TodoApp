package com.digi.jc_500_24_todoapp.screens

sealed class TaskEvent {
    data class UpdateTask(val name: String, val isComplete: Boolean) : TaskEvent()
    data class EditTaskName(val name: String) : TaskEvent()
    data class EditTaskImportance(val isImportant: Boolean) : TaskEvent()
    data object AddTask : TaskEvent()
    data object ToggleSheet : TaskEvent()
}