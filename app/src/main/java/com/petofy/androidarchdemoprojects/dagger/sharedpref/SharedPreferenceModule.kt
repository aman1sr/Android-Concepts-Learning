package com.petofy.androidarchdemoprojects.dagger.sharedpref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.pm.PermissionInfoCompat.ProtectionFlags
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext() : Context {
        return context
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}