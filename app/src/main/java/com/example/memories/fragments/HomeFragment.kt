package com.example.memories.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.NotesDatabase

class HomeFragment : AppCompatActivity() {
    //var note = Note()
    val id = intent.getIntExtra("id", -1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)


        val note = NotesDatabase.getInstance(this).notesDao().searchbyid(id)


        if (note.pathImage != null) {

            changeFragment(imageFragment())
        } else {

            changeFragment(TextFragment())
        }


    }

    fun changeFragment(fragment: Fragment) {
        val args = Bundle()
        args.putInt("id", id)
        fragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_layout, fragment)
            .addToBackStack("")
            .commit()


    }


}


