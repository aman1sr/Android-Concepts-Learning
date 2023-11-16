package com.petofy.androidarchdemoprojects.dagger.cheezyCode

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun saveUser(email: String, password: String) {
        Log.d(DaggerCheezyCodeActivity.TAG, "User Saved")
    }
}