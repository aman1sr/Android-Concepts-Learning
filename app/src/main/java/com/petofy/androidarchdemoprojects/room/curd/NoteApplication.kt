package com.petofy.androidarchdemoprojects.room.curd

import android.app.Application

class NoteApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { NoteRepo(database.noteDao()) }


}