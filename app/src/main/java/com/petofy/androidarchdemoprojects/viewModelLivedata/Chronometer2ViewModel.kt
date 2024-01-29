package com.petofy.androidarchdemoprojects.viewModelLivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Chronometer2ViewModel : ViewModel() {

     private val _isChromoStopped = MutableLiveData<Boolean>(false)
    val isChromoStopped: LiveData<Boolean> = _isChromoStopped

//    lateinit var stuff : Int           // todo: why lateinit is showing error

    val mElapsedTime by lazy { MutableLiveData<Long>() }
    fun getChromoClickState() : Boolean = _isChromoStopped.value!!
    fun setChromoClickstate(state: Boolean) {
        _isChromoStopped.value = state
    }

    init {
     val  mInitialTime = SystemClock.elapsedRealtime()
        viewModelScope.launch {
            while (getChromoClickState()) {
                delay(1000L)
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }

    }

}