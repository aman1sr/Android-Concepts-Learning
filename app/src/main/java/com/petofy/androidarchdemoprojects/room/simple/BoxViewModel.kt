package com.petofy.androidarchdemoprojects.room.simple

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.petofy.androidarchdemoprojects.room.BoxDao
import com.petofy.androidarchdemoprojects.room.Boxer
import com.petofy.androidarchdemoprojects.room.singleArgViewModelFactory

class BoxViewModel(private val boxDao: BoxDao) : ViewModel() {
    var boxerID = 0
    companion object {
        /**
         * Factory for creating [MainViewModel]
         *
         * @param arg the repository to pass to [MainViewModel]
         */
        val FACTORY = singleArgViewModelFactory(::BoxViewModel)
    }
val boxer: LiveData<String?> = boxDao.boxerLiveData.map { it?.name }
//val boxer: LiveData<Boxer?> = boxDao.boxerLiveData
    suspend fun insertBoxerData(boxer: Boxer) {
        boxDao.insertBoxer(boxer)
        boxerID++
    }

    fun extractBoxerDetail() : List<String>? = boxDao.boxerList.map { it?.name.toString() }
}