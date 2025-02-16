package com.petofy.androidarchdemoprojects.room.curd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)   // strategy ignores a new word if it's exactly the same as one already in the list. (https://developer.android.com/reference/androidx/room/OnConflictStrategy.html)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM note_table ORDER BY note ASC")
    fun getAlphabetizedWords(): Flow<List<Note>>
    @Query("SELECT * FROM note_table")
    fun getNotes(): Flow<List<Note>>

    @Query("DELETE FROM note_table where id = :pos")      //todo: check, not working 90% of time
    suspend fun deleteAtPos(pos: Int)
    //        test vs bw ab & below
    @Delete
    suspend fun deleteNotePos(note: Note)
    @Update
    suspend fun update(note: Note)

}