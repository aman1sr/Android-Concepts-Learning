package com.petofy.androidarchdemoprojects.room.simple

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Boxer::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract val boxDao : BoxDao
}
private lateinit var INSTANCE: MyRoomDatabase

fun getDatabase(context: Context): MyRoomDatabase {
    synchronized(MyRoomDatabase::class){
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "boxer_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}