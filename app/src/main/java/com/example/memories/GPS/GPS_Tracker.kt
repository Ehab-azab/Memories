package com.example.memories.GPS

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


//
//GPS_location and tracker 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class GPS_Tracker() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @SuppressLint("MissingPermission")
    fun getLocation(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->

                // Got last known location. In some rare situations this can be null.
            }
    }

    fun checkgpsoppen(context: Context) {

        val REQUEST_CHECK_SETTINGS = 3000

        var builder = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationRequest().setFastestInterval(5000))
            .addLocationRequest(LocationRequest().setSmallestDisplacement(10.0f))
        val result =
            LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())
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
                                context as Activity?,
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

    }


}







