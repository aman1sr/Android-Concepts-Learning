package com.petofy.androidarchdemoprojects.br

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.databinding.ActivityAeroplaneBractivityBinding

class AeroplaneBRActivity : AppCompatActivity() {

    lateinit var binding: ActivityAeroplaneBractivityBinding
    lateinit var receiver: AirplaneModeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAeroplaneBractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver,it)
        }

    }
}