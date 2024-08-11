package com.example.noteappyt.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappyt.common.constant.NoteConstant.TABLE_NAME
import com.example.noteappyt.feature.note.data.NoteDao
import com.example.noteappyt.feature.note.data.NoteDatabase
import com.example.noteappyt.feature.note.domain.repository.NoteRepository
import com.example.noteappyt.feature.note.domain.repository.NoteRepositoryImp
import com.example.noteappyt.notes.domain.usercase.DeleteNote
import com.example.noteappyt.notes.domain.usercase.GetAllNote
import com.example.noteappyt.notes.domain.usercase.GetNoteById
import com.example.noteappyt.notes.domain.usercase.InsertNote
import com.example.noteappyt.notes.domain.usercase.UpdateNote
import com.example.noteappyt.notes.domain.usercase.UserCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            TABLE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImp(dao = db.noteDao())
    }

    @Provides
    @Singleton
    fun provideUserCases(repository: NoteRepository): UserCases{
        return UserCases(
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            deleteNote = DeleteNote(repository),
            getNoteById = GetNoteById(repository),
            getAllNote = GetAllNote(repository)
        )
    }
}