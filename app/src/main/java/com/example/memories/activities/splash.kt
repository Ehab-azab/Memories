package com.example.memories.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.memories.R

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("prefrence", Context.MODE_PRIVATE)
        val firststart = sharedPreferences.getBoolean("firststart", true)
        if (firststart) {
            firsttime()
        } else {
            Handler().postDelayed(Runnable {
                startActivity(Intent(this@splash, Home::class.java))
                this@splash.finish()
            }, 2000)
        }


    }

    fun firsttime() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@splash, Mains_Activity::class.java))
            this@splash.finish()
        }, 2000)
        val sharedPreferences = getSharedPreferences("prefrence", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("firststart", false)
        editor.apply()
    }
}