package com.example.memories.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import kotlinx.android.synthetic.main.fragment_image.*
import me.relex.circleindicator.CircleIndicator3

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


