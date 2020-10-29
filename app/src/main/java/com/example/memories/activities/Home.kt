package com.example.memories.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memories.R
import com.example.memories.adapters.Home_RecyclerView_Adapter
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import com.example.memories.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {

    var note = Note()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        fab.setOnClickListener {
            startActivity(Intent(this, addNotes::class.java))

        }
        appbar.setOnMenuItemClickListener {

            if (it.itemId == R.id.add_photo_from_galary_appBar) {
                startActivity(Intent(this, addNotes::class.java))

            } else if (it.itemId == R.id.capture_photo_appBar) {
                startActivity(Intent(this, addNotes::class.java))

            }
            return@setOnMenuItemClickListener false


        }
        val adapter = Home_RecyclerView_Adapter(
            NotesDatabase.getInstance(this).notesDao().getAllNotes() as ArrayList<Note>
        )
        home_recyclerview.adapter = adapter
        home_recyclerview.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        adapter.onitemclicked = object : Home_RecyclerView_Adapter.OnItemClickListener {
            override fun OnItemClicked(position: Int, noteId: Int?) {
                sendid_andIntent(noteId!!)
            }

        }

    }

    fun sendid_andIntent(itemId: Int) {
        val intent = Intent(this, HomeFragment::class.java)
        intent.putExtra("id", itemId)
        startActivity(intent)
    }


}