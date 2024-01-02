package com.petofy.androidarchdemoprojects.lambda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityRecHomeBinding

class RecHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecHomeBinding
    lateinit var adapter: RecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        val list = getDummyData()
        adapter = RecAdapter(list)
        recyclerView.adapter = adapter
    }

    private fun getDummyData(): ArrayList<String> {
        val list = arrayListOf<String>()
        for (i in 0..10) {
            list.add("$i")
        }
        return list
    }
}