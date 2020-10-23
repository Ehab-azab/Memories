package com.example.memories.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import com.example.memories.R
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class addNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        backbtn_addnotes.setOnClickListener {
            startActivity(Intent(this@addNotes, Home::class.java))
            this@addNotes.finish()
        }
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        time.setText(currentTime.format(DateTimeFormatter.ISO_DATE).toString())
        time.setOnClickListener {
            val datepacker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    //setdate
                    val monthy = mMonth + 1
                    time.text = "$mYear-$monthy-$mDay"
                    Log.e("time", time.text.toString())
                },
                year,
                month,
                day
            )
            datepacker.show()
        }
        Log.e("time", time.text.toString())
        submett.setOnClickListener {
            val date = time.text.toString()
            val title = title_of_note.text.toString()
            val note = note.text.toString()
            //still
            val lat = null
            val long = null
            Log.e("date", date)
            Log.e("title", title)
            Log.e("note", note)
        }
    }

}
