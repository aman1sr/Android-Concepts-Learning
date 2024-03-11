package com.petofy.androidarchdemoprojects.ml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityMovinetBinding

class MovinetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovinetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovinetBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}