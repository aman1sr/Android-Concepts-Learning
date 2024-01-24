package com.petofy.androidarchdemoprojects.viewModelLivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateVM(val state: SavedStateHandle) : ViewModel() {
    val inputLiveData: LiveData<String> = state.getLiveData("data")
    fun setInputData(data: String) {
        state.set("data",data)
    }
}