package com.example.memories.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.database.Note
import com.example.memories.fragments.HomeFragment
import com.example.memories.fragments.imageFragment
import kotlinx.android.synthetic.main.activity_home.*

import kotlinx.android.synthetic.main.activity_home.*
import java.util.jar.Manifest

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


    }

    fun sendid_andIntent() {
        var itemId = 5
        val intent = Intent(this, HomeFragment::class.java)
        intent.putExtra("id", itemId)
        startActivity(intent)
    }


}