package com.petofy.androidarchdemoprojects.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityFirebase2Binding
import com.petofy.androidarchdemoprojects.utils.Utils.startScreen

class FirebaseHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirebase2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebase2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAuth.setOnClickListener {
            startScreen(this,FirebaseAuthActivity::class.java)
        }
        binding.btnStorage.setOnClickListener {
            startScreen(this,FirebaseStorageActivity::class.java)
        }


    }
}