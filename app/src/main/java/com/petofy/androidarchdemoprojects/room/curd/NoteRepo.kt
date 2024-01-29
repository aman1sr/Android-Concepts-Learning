package com.petofy.androidarchdemoprojects.room.curd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepo(private val noteDao: NoteDao) {
    val allNote: Flow<List<Note>> = noteDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }


}