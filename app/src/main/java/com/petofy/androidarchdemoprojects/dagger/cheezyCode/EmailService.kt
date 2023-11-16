package com.petofy.androidarchdemoprojects.dagger.cheezyCode

import android.util.Log
import javax.inject.Inject

class EmailService @Inject constructor(){
    fun send(to: String, from: String, body: String) {
        Log.d(DaggerCheezyCodeActivity.TAG, "Email send")
    }
}