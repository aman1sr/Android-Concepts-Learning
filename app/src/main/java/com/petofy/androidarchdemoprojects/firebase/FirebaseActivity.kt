package com.petofy.androidarchdemoprojects.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityFirebaseBinding
import com.petofy.androidarchdemoprojects.databinding.ActivityFlowBinding

class FirebaseActivity : AppCompatActivity() {

    lateinit var binding: ActivityFirebaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}