package com.petofy.androidarchdemoprojects.viewModelLivedata.custom

class CustomLiveData<T> {
    private var mValue : T? = null
//    private val mObservers = ArrayList<(T?) -> Unit>()

    fun setValue(value: T) {
        mValue = value
    }
    fun getValue(): T? {
        return mValue
    }

}