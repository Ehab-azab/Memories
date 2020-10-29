
package com.example.memories.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.memories.GPS.GPS_Tracker
import com.example.memories.R
import com.example.memories.base.base
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class addNotes : base() {
    val gps_tracker = GPS_Tracker()
    val base = base()
    val imagespathes = mutableListOf<String>()
    val LOCATION_PERMITION_CODE = 3000
    var date: String? = null
    var title: String? = null
    var notes: String? = null
    var lat: Double? = null
    var long: Double? = null
    val REQUEST_CHECK_SETTINGS = 500
    var locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 10000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            lat = result?.lastLocation?.latitude
            long = result?.lastLocation?.longitude
            Log.e("lat", "" + lat)
            Log.e("long", "" + long)
        }

        override fun onLocationAvailability(p0: LocationAvailability?) {
            super.onLocationAvailability(p0)
        }
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkforGPSpermition(context = this)
        setContentView(R.layout.activity_add_notes)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        backbtn_addnotes.setOnClickListener {
            startActivity(Intent(this@addNotes, Home::class.java))
            this@addNotes.finish()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentTime = LocalDateTime.now()
            time.setText(currentTime.format(DateTimeFormatter.ISO_DATE).toString())
        } else {


            var date = Date()
            val formatter = SimpleDateFormat("yyyy MMM dd")
            val datetime: String = formatter.format(date)
            time.setText(datetime)
        }



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

            date = time.text.toString()
            title = title_of_note.text.toString()
            notes = note.text.toString()
            //still
            NotesDatabase.getInstance(this).notesDao().addNote(
                Note(
                    title = title,
                    description = notes,
                    day = day.toString(),
                    month = month.toString(),
                    year = year.toString(),
                    date = date,
                    lat = lat?.toFloat(),
                    longe = long?.toFloat(),
                    pathImage = imagespathes
                )
            )
            startActivity(Intent(this, Home::class.java))
            finish()
            Log.e("date", date!!)
            Log.e("title", title!!)
            Log.e("note", notes!!)
            Log.e("lat_saved", "" + lat)
            Log.e("long_saved", "" + long)

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMITION_CODE) {
            checkforGPSpermition(this)
        } else {
            Toast.makeText(this, "You Shoud Accept Permition", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val states = LocationSettingsStates.fromIntent(intent);

        when (requestCode) {
            REQUEST_CHECK_SETTINGS ->
                when (resultCode) {
                    Activity.RESULT_OK ->
                        // All required changes were successfully made
                        gps_tracker.getLocation(this)
                    Activity.RESULT_CANCELED -> {
                        Toast.makeText(this, "you shoud open GPS to contenu", Toast.LENGTH_LONG)
                            .show()
                    }
                    // The user was asked to change settings, but chose not to

                }

        }
    }

    private fun checkforGPSpermition(context: Context) {
        if (base.checkPermition(
                context = baseContext,
                PermitionName = android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {

            val REQUEST_CHECK_SETTINGS = 3000
            //this to get location


            var builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

            val result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
            result.addOnCompleteListener(OnCompleteListener {
                try {
                    val response = it.getResult(ApiException::class.java);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.

                    getLocation(context)


                } catch (exception: ApiException) {
                    when (exception.getStatusCode()) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                val resolvable = exception as ResolvableApiException
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().

                                resolvable.startResolutionForResult(
                                    this,
                                    REQUEST_CHECK_SETTINGS
                                );
                            } catch (e: IntentSender.SendIntentException) {
                                // Ignore the error.
                            } catch (e: ClassCastException) {
                                // Ignore, should be an impossible error.
                            }
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                        }
                    }
                }
            })

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMITION_CODE
                )
            }
            //locatioreq.requistLocationPermitionFromUser(LOCATION_PERMITION_CODE)
            // base.takeRequest(context = this,chekPermitionName = android.Manifest.permission.ACCESS_FINE_LOCATION,PermitionRequistCode = LOCATION_PERMITION_CODE,txtToShowInMessage = getString(R.string.why_this_Permition),arrayOfRequests = android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }


}
