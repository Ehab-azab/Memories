package com.example.memories.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.Note
import com.example.memories.fragments.imageFragment


class Home : AppCompatActivity() {

    var note = Note()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)






    }



}