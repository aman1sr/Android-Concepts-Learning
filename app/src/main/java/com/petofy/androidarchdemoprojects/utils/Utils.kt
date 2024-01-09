package com.petofy.androidarchdemoprojects.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

object Utils {
    fun startScreen(context: Context, className: Class<*>) {
        val intent = Intent(context, className)
        context.startActivity(intent)
    }
}