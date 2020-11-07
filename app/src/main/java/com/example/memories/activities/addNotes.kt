
package com.example.memories.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memories.*
import com.example.memories.GPS.GPS_Tracker
import com.example.memories.Requists.Requests
import com.example.memories.Time.Time
import com.example.memories.adapters.recyclerView_Adapter_for_photos
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import com.example.memories.images.images
import com.example.memories.messages.messages
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class addNotes : images() {
    //classes
//    var imagesclass=images()
    val messages = messages()
    var locationclass = GPS_Tracker()
    val requests = Requests()
    val timeclass = Time()

    //data parametars
    val imagespathes = mutableListOf<String>()
    var date: String? = null
    var title: String? = null
    var notes: String? = null
    var lat: Double? = null
    var long: Double? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        checkandgettingLocationpermission()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentTime = LocalDateTime.now()
            time.setText(currentTime.format(DateTimeFormatter.ISO_DATE).toString())
        } else {
            var date = Date()
            val formatter = SimpleDateFormat("dd MM yyyy")
            val datetime: String = formatter.format(date)
            time.setText(datetime)
        }



        backbtn_addnotes.setOnClickListener {
            startActivity(Intent(this@addNotes, Home::class.java))
            this@addNotes.finish()
        }
        time.setOnClickListener {
            val datepacker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    //setdate
                    val monthy = mMonth + 1
                    time.text = "$mDay $monthy $mYear"
                    Log.e("time", time.text.toString())
                },
                timeclass.year,
                timeclass.month,
                timeclass.day
            )
            datepacker.show()
        }
        Log.e("time", time.text.toString())


        galary.setOnClickListener {

            if (requests.isPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                activeGallery(this, GALARY_PERMITION_CODE)
            } else {
                requests.getPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    GALARY_PERMITION_CODE,
                    getString(
                        R.string.why_we_want_galary_permission
                    )
                )
            }

        }
        camera.setOnClickListener {
            if (requests.isPermissionGranted(
                    this,
                    Manifest.permission.CAMERA
                ) && requests.isPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) {
                // activeTakePhoto(CAMERA_REQUEST_CODE)
                activeTakePhoto(CAMERA_AND_GALARY_REQUEST_CODE)

            } else {
                if (requests.isPermissionGranted(this, Manifest.permission.CAMERA) != true) {
                    requests.getPermission(
                        this, Manifest.permission.CAMERA, CAMERA_PERMITION_CODE, getString(
                            R.string.why_we_want_Camera_permission
                        )
                    )
                }
                if (requests.isPermissionGranted(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != true
                ) {
                    requests.getPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        GALARY_PERMITION_CODE,
                        getString(
                            R.string.why_we_want_galary_permission
                        )
                    )
                }

            }

        }
        submett.setOnClickListener {

            date = time.text.toString()
            title = title_of_note.text.toString()
            notes = note.text.toString()

            if (lat == null || long == null) {
                messages.showDialog(context = this,
                    message = "We cant Get Your Location To save it If u Want to Save Location Please Wait",
                    positiveBtnName = "OK",
                    positiveBtnAction = DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        //add Requist

                    },
                    negativeBtnName = "Save Without Location",
                    negativeBtnAction = DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        if (title_of_note.text == null || note.text == null) {
                            Toast
                                .makeText(
                                    this,
                                    "Please Fill Title Or Description",
                                    Toast.LENGTH_LONG
                                ).show()
                        } else {
                            NotesDatabase.getInstance(this).notesDao().addNote(
                                Note(

                                    title = title,
                                    description = notes,
                                    day = timeclass.day.toString(),
                                    month = timeclass.month.toString(),
                                    year = timeclass.year.toString(),
                                    date = date,
                                    lat = lat?.toFloat(),
                                    longe = long?.toFloat(),
                                    pathImage = imagespathes
                                )
                            )
                            startActivity(Intent(this, Home::class.java))

                            Log.e("date", date!!)
                            Log.e("title", title!!)
                            Log.e("note", notes!!)
                            Log.e("lat_saved", "" + lat)
                            Log.e("long_saved", "" + long)
                            Log.e("not", "" + imagespathes)
                        }
                    })
            } else {
                if (title_of_note.text == null || note.text == null) {
                    Toast.makeText(this, "Please Fill Title Or Description", Toast.LENGTH_LONG)
                        .show()
                } else {
                    NotesDatabase.getInstance(this).notesDao().addNote(
                        Note(

                            title = title,
                            description = notes,
                            day = timeclass.day.toString(),
                            month = timeclass.month.toString(),
                            year = timeclass.year.toString(),
                            date = date,
                            lat = lat?.toFloat(),
                            longe = long?.toFloat(),
                            pathImage = imagespathes
                        )
                    )
                    startActivity(Intent(this, Home::class.java))

                    Log.e("date", date!!)
                    Log.e("title", title!!)
                    Log.e("note", notes!!)
                    Log.e("lat_saved", "" + lat)
                    Log.e("long_saved", "" + long)
                    Log.e("not", "" + imagespathes)
                }
            }


        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requests.mangeResponse(
                requestCode,
                LOCATION_PERMISSION_CODE,
                permissions,
                grantResults,
                this,
                packageName
            )
        ) {
            updatelocation()
        }
        if (requests.mangeResponse(
                requestCode,
                GALARY_PERMITION_CODE,
                permissions,
                grantResults,
                this,
                packageName
            )
        ) {
            activeGallery(this, GALARY_PERMITION_CODE)
        }
        if (requests.mangeResponse(
                requestCode,
                CAMERA_PERMITION_CODE,
                permissions,
                grantResults,
                this,
                packageName
            )
        ) {
            activeTakePhoto(CAMERA_AND_GALARY_REQUEST_CODE)
        }
    }


    @SuppressLint("LongLogTag")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        when (requestCode) {
            LOCATION_PERMISSION_CODE ->
                if (requests.isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    updatelocation()
                    when (resultCode) {
                        Activity.RESULT_OK ->
                            // All required changes were successfully made
                            locationclass.getuserLocation(this)
                        Activity.RESULT_CANCELED -> {
                            Toast.makeText(this, "you shoud open GPS to contenu", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

            GALARY_PERMITION_CODE -> {
                if (requestCode == GALARY_PERMITION_CODE && resultCode == Activity.RESULT_OK && null != data) {
                    imagespathes.add(takeImagePathFromGalary(data))
                    //here Your COde
                    Log.e("gone ya gone", takeImagePathFromGalary(data))
                    sendDatatoAdapter()
                }
                if (requestCode == CAMERA_PERMITION_CODE && resultCode == Activity.RESULT_OK) {
                    imagespathes.add(takeImagePathFromGalary(data))
                }

            }


            CAMERA_AND_GALARY_REQUEST_CODE -> {
                if (requestCode == CAMERA_AND_GALARY_REQUEST_CODE && resultCode == Activity.RESULT_OK && null != data) {
                    Log.e("bgrbellcodebta3imgbath", takeImagePathFromGalary(data))
                    imagespathes.add(takeImagePathFromGalary(data))
                    Log.e("path", imagespathes.toString())
                    sendDatatoAdapter()
                }

            }


        }
    }


    fun sendDatatoAdapter() {
        if (imagespathes.size > 0) {
            galary_Contener_recyclerView.visibility = View.VISIBLE
            val adapter = recyclerView_Adapter_for_photos(imagespathes)
            galary_Contener_recyclerView.adapter = adapter

            galary_Contener_recyclerView.layoutManager =
                GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
            adapter.OnCloseClick = object : recyclerView_Adapter_for_photos.OnImageClickListner {
                override fun OnClick(position: Int) {
                    imagespathes.removeAt(position)
                    adapter.notifyDataSetChanged()
                }

            }

        }
    }

    fun checkandgettingLocationpermission() {
        if (requests
                .isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)
        ) {

            updatelocation()
        } else {
            requests.getPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_PERMISSION_CODE, getString(R.string.why_we_need_location_permission)
            )

        }
    }


    fun updatelocation() {
        locationclass.createLocationRequest()
        locationclass.checkGPSswtting(this)
        locationclass.defineLocationUpdateCallback(object :
            GPS_Tracker.OnLocationUpdateListener {
            override fun onLocationUpdate(location: Location) {
                lat = location.latitude
                long = location.longitude
                Log.e("lat_saved", "" + lat)
                Log.e("long_saved", "" + long)
            }
        })
    }


}
