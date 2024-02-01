package com.petofy.androidarchdemoprojects.webSocket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityWebSocketServerBinding

class WebSocketServerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebSocketServerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebSocketServerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}