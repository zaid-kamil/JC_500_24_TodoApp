package com.digi.jc_500_24_todoapp.screens

sealed class TaskEvent {
    data object AddTask : TaskEvent()
    data class UpdateTask(val isComplete: Boolean) : TaskEvent()
}