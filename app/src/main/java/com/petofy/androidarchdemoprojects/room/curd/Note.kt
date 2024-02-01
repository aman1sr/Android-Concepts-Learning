package com.petofy.androidarchdemoprojects.room.curd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
  class Note(
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "description") val desc: String?,
    @ColumnInfo(name = "priority") val priority: Int,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
 )
