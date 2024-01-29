package com.petofy.androidarchdemoprojects.viewModelLivedata.custom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.petofy.androidarchdemoprojects.viewModelLivedata.HomeVMActivity

class TestCustomVM : ViewModel() {
    val liveData = CustomLiveData<String>()
    val liveData2 = CustomLiveDataObserver<String>()

    init {
        liveData.setValue("aman")
    }
}