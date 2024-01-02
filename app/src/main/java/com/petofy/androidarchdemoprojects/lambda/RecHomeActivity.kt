package com.petofy.androidarchdemoprojects.lambda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityRecHomeBinding
/*
* INTERFACE click:   we made an interface (kind of contract), implemented it in this Activity,
*                 passed our class reference to Adapter , where we have declared private (val listener: ItemClickListener) reference of Interface
*                => RunTime polymorphism   ( now from adpter will be able to access the ovverride f() of this Activity)
*
* Lambda click:
* */
class RecHomeActivity : AppCompatActivity() , ItemClickListener{
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
        adapter = RecAdapter(list,this)     // passing reference of this class for Interface click event
        adapter.onItemClick = {             // click event via lambda
            Toast.makeText(this, "click lambda at $it", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
    }

    private fun getDummyData(): ArrayList<String> {
        val list = arrayListOf<String>()
        for (i in 0..10) {
            list.add("$i")
        }
        return list
    }

    override fun onItemClicked(pos: Int) {
        Toast.makeText(this, "clicked: $pos", Toast.LENGTH_SHORT).show()
    }
}