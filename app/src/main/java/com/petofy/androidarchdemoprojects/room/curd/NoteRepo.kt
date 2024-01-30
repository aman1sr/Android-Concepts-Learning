package com.petofy.androidarchdemoprojects.room.curd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepo(private val noteDao: NoteDao) {
    val allNote: Flow<List<Note>> = noteDao.getNotes()      // todo: can filter wrt query
//    val allNote: Flow<List<Note>> = noteDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")       //  To suppress a warning that the compiler would otherwise issue.
    @WorkerThread                       // : To enforce that the function must be executed on a background thread, not on the main thread.
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(note: Note){
        noteDao.update(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteNote(note: Note){
        noteDao.deleteNotePos(note)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteByQuery(pos: Int){
        noteDao.deleteAtPos(pos)
    }



}