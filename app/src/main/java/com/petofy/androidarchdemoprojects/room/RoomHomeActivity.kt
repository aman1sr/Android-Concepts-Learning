package com.petofy.androidarchdemoprojects.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityHomeRoomBinding
import com.petofy.androidarchdemoprojects.databinding.ActivityRoomHomeBinding
import com.petofy.androidarchdemoprojects.room.curd.TodoActivity
import com.petofy.androidarchdemoprojects.room.simple.RoomSimpleActivity
import com.petofy.androidarchdemoprojects.utils.Utils

class RoomHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimple.setOnClickListener {
            Utils.startScreen(this, RoomSimpleActivity::class.java)
        }
        binding.btnTodoApp.setOnClickListener {
            Utils.startScreen(this, TodoActivity::class.java)
        }

        
    }
}