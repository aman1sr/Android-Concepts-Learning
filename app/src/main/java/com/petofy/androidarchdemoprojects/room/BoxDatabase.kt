package com.petofy.androidarchdemoprojects.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Boxer::class], version = 1, exportSchema = false)
abstract class BoxDatabase : RoomDatabase() {
    abstract val boxDao : BoxDao
}
private lateinit var INSTANCE: BoxDatabase

fun getDatabase(context: Context): BoxDatabase{
    synchronized(BoxDatabase::class){
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    BoxDatabase::class.java,
                    "boxer_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}