package com.petofy.androidarchdemoprojects.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BoxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoxer(boxer: Boxer)

    @get:Query("select * from Boxer")
    val boxerLiveData : LiveData<Boxer?>

    @get:Query("select * from Boxer where id = 0")
    val boxerList : List<Boxer?>

}