package com.petofy.androidarchdemoprojects.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

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
    suspend fun insertBoxerData(boxer: Boxer) {
        boxDao.insertBoxer(boxer)
        boxerID++
    }
    fun extractBoxerDetail() : List<String>? = boxDao.boxerList.map { it?.name.toString() }

}