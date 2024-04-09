package com.aman.mylibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aman.mylibrary.databinding.ActivityJetpackBaseBinding

class JetpackBaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityJetpackBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetpackBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}