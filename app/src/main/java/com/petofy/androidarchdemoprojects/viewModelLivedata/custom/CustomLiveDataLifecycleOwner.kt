package com.petofy.androidarchdemoprojects.viewModelLivedata.custom

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class CustomLiveDataLifecycleOwner<T> {
    private var mValue : T? = null
    private val mObservers: HashMap<(T?) -> Unit, LifecycleOwner> = HashMap()

    fun setValue(value: T) {
        mValue = value
        for ((observer, owner) in mObservers) {
            if(owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                observer.invoke(mValue)
        }
    }
    fun getValue(): T? {
        return mValue
    }
    fun observe(owner: LifecycleOwner, observer: (T?) -> Unit){
        mObservers[observer] = owner
    }
    fun removeObserver(observer: (T?) -> Unit) {
        mObservers.remove(observer)
    }
    private fun notifyChange(lifecycleObserver: LiveDataLifecycleObserver) {
        lifecycleObserver.observer.invoke(mValue)
    }
    private inner class LiveDataLifecycleObserver(
        val owner: LifecycleOwner,
        val observer: (T?) -> Unit
    ) : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun onStarted() {
            notifyChange(this)
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        private fun onResumed() {
            notifyChange(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private fun onDestroyed() {
            removeObserver(observer)
        }
    }
}