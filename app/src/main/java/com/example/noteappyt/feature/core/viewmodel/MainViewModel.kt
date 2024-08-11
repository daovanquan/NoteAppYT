package com.example.noteappyt.feature.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappyt.common.util.Response
import com.example.noteappyt.feature.note.domain.model.Note
import com.example.noteappyt.notes.domain.usercase.UserCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userCases: UserCases
): ViewModel() {
    var note by mutableStateOf(
        Note(0,"",null,false)
    )

    private var deleteNote: Note? = null

    private val _response = MutableStateFlow<Response<List<Note>>>(Response.Loading)
    val response = _response.asStateFlow()

    init {
        getAllNote()
    }

    private fun getAllNote() = viewModelScope.launch {
        userCases.getAllNote()
            .onStart {
                _response.value = Response.Loading
            }.catch {
                _response.value = Response.Error(it)
            }.collect {
                _response.value = Response.Success(it)
            }
    }

    fun insert(note: Note){
        viewModelScope.launch {
            userCases.insertNote(note = note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch {
            userCases.updateNote(note = note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch {
            userCases.deleteNote(note = note)
        }
    }

    fun undoDelete(){
        viewModelScope.launch {
            deleteNote?.let { note ->
                userCases.insertNote(note = note)
            }
        }
    }

    fun getNoteById(noteID: Int){
        viewModelScope.launch {
            userCases.getNoteById(noteId = noteID)
        }
    }

    fun updateNoteTitle(newValue: String){
        note = note.copy(
            title = newValue
        )
    }

    fun updateNoteDescription(newValue: String){
        note = note.copy(
            description = newValue
        )
    }

    fun updateBookmarked(newValue: Boolean){
        note = note.copy(
            isBookmarked = newValue
        )
    }
}