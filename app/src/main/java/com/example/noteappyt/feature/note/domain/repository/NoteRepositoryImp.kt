package com.example.noteappyt.feature.note.domain.repository

import com.example.noteappyt.feature.note.data.NoteDao
import com.example.noteappyt.feature.note.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val dao: NoteDao
): NoteRepository {
    override suspend fun insert(note: Note) {
        dao.insert(note)
    }

    override suspend fun update(note: Note) {
        dao.update(note)
    }

    override suspend fun delete(note: Note) {
        dao.delete(note)
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }
}