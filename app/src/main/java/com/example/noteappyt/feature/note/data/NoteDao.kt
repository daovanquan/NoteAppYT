package com.example.noteappyt.feature.note.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteappyt.common.constant.NoteConstant.TABLE_ID
import com.example.noteappyt.common.constant.NoteConstant.TABLE_NAME
import com.example.noteappyt.feature.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM $TABLE_NAME WHERE $TABLE_ID = :id")
    suspend fun getNoteById(id: Int): Note

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $TABLE_ID ASC ")
    fun getAllNotes(): Flow<List<Note>>

}