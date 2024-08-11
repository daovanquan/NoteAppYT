package com.example.noteappyt.notes.domain.usercase

data class UserCases(
    val insertNote: InsertNote,
    val updateNote: UpdateNote,
    val deleteNote: DeleteNote,
    val getNoteById: GetNoteById,
    val getAllNote: GetAllNote
)
