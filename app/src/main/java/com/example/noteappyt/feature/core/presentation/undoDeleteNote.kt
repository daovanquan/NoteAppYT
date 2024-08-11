package com.example.noteappyt.feature.core.presentation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.example.noteappyt.feature.core.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun undoDeleteNote(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val result = snackbarHostState
            .showSnackbar(
                message = "Note Deleted",
                actionLabel = "Undo",
                duration = SnackbarDuration.Short
            )
        when(result){
            SnackbarResult.ActionPerformed -> {
                viewModel.undoDelete()
            }
            SnackbarResult.Dismissed -> {

            }
        }
    }
}