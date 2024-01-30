package com.petofy.androidarchdemoprojects.room.curd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM note_table ORDER BY note ASC")
    fun getAlphabetizedWords(): Flow<List<Note>>

    @Query("DELETE FROM note_table where id = :pos")
    suspend fun deleteAtPos(pos: Int)
    //        test vs bw ab & below
    @Delete
    suspend fun deleteNotePos(note: Note)

}