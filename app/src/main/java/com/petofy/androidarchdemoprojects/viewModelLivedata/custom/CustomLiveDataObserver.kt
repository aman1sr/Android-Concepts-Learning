package com.petofy.androidarchdemoprojects.viewModelLivedata.custom

class CustomLiveDataObserver<T> {
    private var mValue: T? = null
    private val mObserver = ArrayList<(T?) -> Unit>()

    fun setValue(value: T) {
        mValue = value
        // code establishes a mechanism for notifying interested parties (observers) whenever the internal value mValue changes.
        for (observer in mObserver) {      // todo: how working
            observer.invoke(mValue)
        }
    }
    fun getValue(): T?{
        return mValue
    }
    fun observe(observer: (T?) -> Unit) {
        mObserver.add(observer)
    }
    fun removeObserve(observe: (T?) -> Unit) {
        mObserver.remove(observe)
    }
}