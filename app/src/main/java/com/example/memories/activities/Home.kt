package com.example.memories.activities

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memories.GALARY_PERMITION_CODE
import com.example.memories.R
import com.example.memories.Requists.Requests
import com.example.memories.adapters.Home_RecyclerView_Adapter
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {
    // val GALARY_PERMITION_CODE = 101
    val requests = Requests()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = Home_RecyclerView_Adapter(
            NotesDatabase.getInstance(this).notesDao().getAllNotes() as ArrayList<Note>
        )
        setContentView(R.layout.activity_home)

        if (requests.isPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            home_recyclerview.adapter = adapter
        } else {
            requests.getPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE, GALARY_PERMITION_CODE, getString(
                    R.string.why_we_want_galary_permission_in_Home_Activity
                )
            )
        }
        adapter.notifyDataSetChanged()
        home_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.onitemclicked = object : Home_RecyclerView_Adapter.OnItemClickListener {
            override fun OnItemClicked(position: Int, noteId: Int?) {
                if (noteId != null) {
                    sendid_andIntent(noteId)
                }
            }

        }


        fab.setOnClickListener {
            startActivity(Intent(this, addNotes::class.java))

        }
    }

    fun sendid_andIntent(itemId: Int) {
        val intentty: Intent = Intent(this@Home, showMemory::class.java)
        intentty.putExtra("itemid", itemId)
        startActivity(intentty)
        finish()
        Log.e("ggggggggggggggggggggg", "" + itemId)

    }


}