package com.petofy.androidarchdemoprojects.room.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.petofy.androidarchdemoprojects.databinding.ActivityHomeRoomBinding
import kotlinx.coroutines.launch

/*
* ROOM primary Doc marked (https://developer.android.com/training/data-storage/room#components)
*
* */
class RoomSimpleActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeRoomBinding
    companion object{
        val TAG = "RoomHomeActivity_d"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = getDatabase(this)
        val viewModel = ViewModelProvider(this, BoxViewModel.FACTORY(database.boxDao))
            .get(BoxViewModel::class.java)

        binding.btnInsertData.setOnClickListener {
            val name: String? = binding.editTextText.text.trim().toString()
            binding.editTextText.setText("")
            if (name == null || name == "") {
                return@setOnClickListener
            }
            Toast.makeText(this, "Inserting Data", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Boxer Obj :${Boxer(name,viewModel.boxerID)} ")
            lifecycleScope.launch {
                viewModel.insertBoxerData(Boxer(name,viewModel.boxerID))
            }
        }

        viewModel.boxer.observe(this){name ->
            name?.let {
                binding.txtShowData.text = it
            }
        }

        binding.btnFetchData.setOnClickListener {
            Log.d(TAG, "list: ${viewModel.extractBoxerDetail()} ")
        }
    }
}