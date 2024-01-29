package com.petofy.androidarchdemoprojects.room.curd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityTodoBinding
import com.petofy.androidarchdemoprojects.databinding.AlertDialogEtBinding

class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding
    lateinit var adapter : NoteListAdapter
     var noteList = arrayListOf<Note>()
    private val viewModel : NoteViewModel  by viewModels {
        NoteViewModel.WordViewModelFactory((application as NoteApplication).repository)
    }
    companion object{
        val TAG = "ROOM_Todo_d"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            openNotesAlertDialog()
        }
        setRecview()

        observeNotes()


    }

    private fun observeNotes() {
        viewModel.allNotes.observe(this, Observer { note ->
            note?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun setRecview() {
        adapter = NoteListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

    }

    private fun openNotesAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Enter Your NOTES")
        val bind = AlertDialogEtBinding.inflate(inflater)

        /*
        * Below line wasn't able to extract text & add in list on pressing OK btn because :::Access UI elements within their relevant event handlers to ensure they've been properly initialized and interacted with by the user.
        * */
//        val note = bind.etNote.text.toString()

        builder.setView(bind.root)
        builder.setPositiveButton("OK"){dialogInterface, i ->
            val etNote = bind.etNote.text.toString()
//            noteList.add(Note(note))
            Log.d(TAG, "editText note:$etNote ,,,,, noteList : ${noteList} ")
            val note = Note(etNote)
            viewModel.insert(note)
        }
        builder.show()

    }
}