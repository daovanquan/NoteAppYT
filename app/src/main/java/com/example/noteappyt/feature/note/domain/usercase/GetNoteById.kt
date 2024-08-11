package com.example.noteappyt.notes.domain.usercase

import com.example.noteappyt.feature.note.domain.model.Note
import com.example.noteappyt.feature.note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteById @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int): Note {
        return repository.getNoteById(noteId)
    }
}