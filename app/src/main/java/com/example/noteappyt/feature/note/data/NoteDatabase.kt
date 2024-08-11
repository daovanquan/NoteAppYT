package com.example.noteappyt.feature.note.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappyt.common.constant.NoteConstant.TABLE_VERSION
import com.example.noteappyt.feature.note.domain.model.Note

@Database(entities = [Note::class], version = TABLE_VERSION, exportSchema = true)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}