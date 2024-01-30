package com.petofy.androidarchdemoprojects.room.curd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityTodoBinding
import com.petofy.androidarchdemoprojects.databinding.AlertDialogEtBinding
/*
* room CURD operation (https://medium.com/huawei-developers/room-database-with-kotlin-mvvm-architecture-477c3ad3c264)
*
* */
class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding
    lateinit var adapter : NoteListAdapter
     var noteList : List<Note>? = null
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
            openNotesAlertDialog(null)  // insert case
        }
        setRecview()

        observeNotes()
        itemSwipeDelete()

        updateNoteLambdaClicked()

    }

    private fun updateNoteLambdaClicked() {
        adapter.noteItemLambda = {
            openNotesAlertDialog(it)
        }
    }

    // itemSwipeDelete: (https://www.geeksforgeeks.org/android-swipe-to-delete-and-undo-in-recyclerview-with-kotlin/)
    private fun itemSwipeDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.deleteNote(noteList?.get(pos)!!)
//                viewModel.deleteNotebyQuery(pos)
            }
        }).attachToRecyclerView(binding.recyclerview)
    }

    private fun observeNotes() {
        viewModel.allNotes.observe(this, Observer { note ->
            note?.let {
                noteList = it
                adapter.submitList(it)
            }
        })
    }

    private fun setRecview() {
        adapter = NoteListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

    }

    private fun openNotesAlertDialog(pos: Int?) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Enter Your NOTES")
        val bind = AlertDialogEtBinding.inflate(inflater)

        pos?.let {pos ->
            bind.etNote.setText(noteList?.get(pos)?.note?.toString())
            bind.etDesc.setText(noteList?.get(pos)?.desc?.toString())
            bind.etPriority.setText(noteList?.get(pos)?.priority?.toString())
        }
        /*
        * Below line wasn't able to extract text & add in list on pressing OK btn because :::Access UI elements within their relevant event handlers to ensure they've been properly initialized and interacted with by the user.
        * */
//        val note = bind.etNote.text.toString()

        builder.setView(bind.root)
        builder.setPositiveButton("OK"){dialogInterface, i ->
            val etNote = bind.etNote.text.toString()
            var etDesc : String?  = bind.etDesc.text.toString()
            val priority = bind.etPriority.text.toString()

            if(etNote.isNullOrBlank() && priority.isNullOrBlank()) return@setPositiveButton
            if(etDesc.isNullOrBlank()) etDesc = null
            Log.d(TAG, "editText note:$etNote ,,,,, noteList : ${noteList} ")

            if (pos == null) {  // insert case
                val note = Note(etNote, etDesc, priority.toInt())
                viewModel.insert(note)
            } else {
                val updateNote = Note(etNote, etDesc, priority.toInt(),noteList?.get(pos)?.id)
                viewModel.updateNote(updateNote)
            }

        }
        builder.show()

    }
}