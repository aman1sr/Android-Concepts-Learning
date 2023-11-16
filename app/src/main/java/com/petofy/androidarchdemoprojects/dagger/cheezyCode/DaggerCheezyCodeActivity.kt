package com.petofy.androidarchdemoprojects.dagger.cheezyCode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R

class DaggerCheezyCodeActivity : AppCompatActivity() {
    companion object{
         val TAG = "DaggerCheezyCodeActivity_d"
    }
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_cheezy_code)

         val userRegistrationService =
             DaggerUserRegistrationComponent.builder().build().getUserRegistrationService()
         userRegistrationService.registerUser("aman.singh@cynoteck.com", "123456")



    }
}