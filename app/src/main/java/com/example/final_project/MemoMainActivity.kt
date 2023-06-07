package com.example.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MemoMainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo_activity_main)

        //var NickName : String? = intent.getStringExtra(FirstActivity.NICKNAME)
        //Toast.makeText(this@MainActivity,"${NickName}",Toast.LENGTH_SHORT)
        notesRV = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this,this,this)
        notesRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, { list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent = Intent(this@MemoMainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle}Deiete",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MemoMainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }
}