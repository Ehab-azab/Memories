package com.example.memories.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.memories.R
import kotlinx.android.synthetic.main.activity_home.*
import java.util.jar.Manifest

class Home : AppCompatActivity() {
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

}