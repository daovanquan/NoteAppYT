package com.example.noteappyt.feature.note.domain.model

import androidx.navigation.Navigator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappyt.common.constant.NoteConstant.TABLE_BOOKMARKED
import com.example.noteappyt.common.constant.NoteConstant.TABLE_DESCRIPTION
import com.example.noteappyt.common.constant.NoteConstant.TABLE_ID
import com.example.noteappyt.common.constant.NoteConstant.TABLE_NAME
import com.example.noteappyt.common.constant.NoteConstant.TABLE_TITLE

@Entity(tableName = TABLE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TABLE_ID)
    val id: Int = 0,
    @ColumnInfo(name = TABLE_TITLE)
    val title: String,
    @ColumnInfo(name = TABLE_DESCRIPTION)
    val description: String? = null,
    @ColumnInfo(name = TABLE_BOOKMARKED)
    val isBookmarked: Boolean = false
)
