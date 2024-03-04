package com.digi.jc_500_24_todoapp.screens

import com.digi.jc_500_24_todoapp.data.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val error: String = "",
    val status: String = "",
)
