package com.example.memories.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.Note
import kotlinx.android.synthetic.main.fragment_image.*
import me.relex.circleindicator.CircleIndicator3

class HomeFragment : AppCompatActivity() {
    var note = Note()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)



        /*if(note.pathImage!=null){
            changeFragment(imageFragment())
        }else{
            changeFragment(TextFragment())
        }*/


    }
    fun changeFragment(fragment: Fragment){

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_layout,fragment)
            .addToBackStack("")
            .commit()

    }


}