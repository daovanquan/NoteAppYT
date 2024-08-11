package com.example.noteappyt.notes.domain.usercase

import com.example.noteappyt.feature.note.domain.model.Note
import com.example.noteappyt.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}