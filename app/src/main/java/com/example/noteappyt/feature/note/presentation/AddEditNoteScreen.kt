package com.example.noteappyt.feature.note.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappyt.feature.core.presentation.toastMsg
import com.example.noteappyt.feature.core.ui.theme.poppinsFontFamily
import com.example.noteappyt.feature.core.ui.theme.ubuntoFontFamily
import com.example.noteappyt.feature.core.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusRequest = FocusRequester()
    val textStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = poppinsFontFamily
    )
    LaunchedEffect(key1 = true) {
        if (noteId > 0) {
            viewModel.getNoteById(noteId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = if (noteId > 0) "Edit Note" else "Add Note",
                        fontFamily = ubuntoFontFamily
                    )
                },
                actions = {
                    IconToggleButton(
                        checked = viewModel.note.isBookmarked,
                        onCheckedChange = {
                            viewModel.updateBookmarked(it)
                            viewModel.update(viewModel.note.copy(isBookmarked = it))
                        }) {
                        Icon(
                            imageVector = if (viewModel.note.isBookmarked) Icons.Filled.Bookmark
                            else Icons.Filled.BookmarkAdd,
                            contentDescription = null
                        )

                    }

                    IconButton(onClick = {
                        if (viewModel.note.title.isNotBlank()) {
                            viewModel.insert(viewModel.note)
                            navController.popBackStack()
                        } else {
                            toastMsg(
                                context,
                                "Title can't not empty"
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
    ) {
            LaunchedEffect(key1 = true) {
                if (noteId == -1) {
                    focusRequest.requestFocus()
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .focusRequester(focusRequest),
                shape = RectangleShape,
                textStyle = textStyle,
                value = viewModel.note.title,
                onValueChange = {
                    viewModel.updateNoteTitle(it)
                },
                placeholder = {
                    Text(
                        text = "Add Title ...",
                        style = textStyle
                    )
                })
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .focusRequester(focusRequest),
                shape = RectangleShape,
                textStyle = textStyle,
                value = viewModel.note.description ?: "",
                onValueChange = {
                    viewModel.updateNoteDescription(it)
                },
                placeholder = {
                    Text(
                        text = "Add Description ...",
                        style = textStyle
                    )
                })
        }
    }
}