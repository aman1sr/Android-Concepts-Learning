package com.petofy.androidarchdemoprojects.viewModelLivedata

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.StartupTime

public class ChronometerViewModel :  ViewModel() {

    private var mStartTime :Long? = null

    public fun setStartTime( startTime : Long) {
        this.mStartTime = startTime
        Log.d(HomeVMActivity.TAG, "mStartTime: ${startTime} ")
    }
    fun getStartTime(): Long?{
        return mStartTime
    }


}