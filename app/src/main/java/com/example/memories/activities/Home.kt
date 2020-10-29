package com.example.memories.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.Note
import com.example.memories.fragments.HomeFragment
import com.example.memories.fragments.imageFragment
import kotlinx.android.synthetic.main.activity_home.*


class Home : AppCompatActivity() {

    var note = Note()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fab.setOnClickListener {

        }


    }

    fun sendid_andIntent() {
        var itemId = 5
        val intent = Intent(this, HomeFragment::class.java)
        intent.putExtra("id", itemId)
        startActivity(intent)
    }


}