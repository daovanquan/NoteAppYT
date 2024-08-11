package com.example.noteappyt.notes.domain.usercase

import com.example.noteappyt.feature.note.domain.model.Note
import com.example.noteappyt.feature.note.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) =
        repository.insert(note)
}