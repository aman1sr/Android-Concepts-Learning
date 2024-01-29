package com.petofy.androidarchdemoprojects.room.simple

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Boxer(val name: String, @PrimaryKey val id: Int =0 )