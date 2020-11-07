package com.example.memories.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.NotesDatabase
import com.example.memories.fragments.TextFragment
import com.example.memories.fragments.imageFragment

class showMemory : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)
        val intent2: Intent = getIntent()

        var id = intent2.getIntExtra("itemid", -1)
        //Log.e("ggggggggggggggggggggg", ""+id)


        val note = NotesDatabase.getInstance(this).notesDao().searchbyid(id)


        if (note.pathImage!!.size > 0) {

            changeFragment(imageFragment(), id)

        } else {

            changeFragment(TextFragment(), id)

        }



    }

    fun changeFragment(fragment: Fragment, id: Int) {
        val args = Bundle()

        args.putInt("id", id)
        fragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_layout, fragment)
            .addToBackStack("")
            .commit()
        //this@HomeFragment.finish()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intentty = Intent(this@showMemory, Home::class.java)
        startActivity(intentty)
        finish()
    }


}


