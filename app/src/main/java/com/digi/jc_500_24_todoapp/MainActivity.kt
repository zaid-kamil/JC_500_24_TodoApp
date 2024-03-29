package com.digi.jc_500_24_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digi.jc_500_24_todoapp.screens.TaskScreen
import com.digi.jc_500_24_todoapp.screens.TaskViewModel
import com.digi.jc_500_24_todoapp.ui.theme.JC_500_24_TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_500_24_TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm: TaskViewModel = viewModel()
                    TaskScreen(
                        uiState = vm.uiState.collectAsState().value,
                        onEvent = vm::onEvent
                    )
                }
            }
        }
    }
}
