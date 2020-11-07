package com.example.memories.GPS

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import com.example.memories.LOCATION_CHECK_SETTINGS
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task


//
//GPS_location and tracker 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class GPS_Tracker() {
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    lateinit var fusedLocationClient: FusedLocationProviderClient

    fun createLocationRequest() {
        //Create the location request and set the parameters
        locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    fun checkGPSswtting(context: Context) {

        //get the current location settings of a user's device.
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        // check whether the current location settings are satisfied
        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...


            //TODO if user acept
            getuserLocation(context)
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        context as Activity,
                        LOCATION_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    fun defineLocationUpdateCallback(onLocationUpdateListener: OnLocationUpdateListener) {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    onLocationUpdateListener.onLocationUpdate(location)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getuserLocation(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun stopgettinguserLocation() {
        if (this::fusedLocationClient.isInitialized)
            fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    interface OnLocationUpdateListener {
        fun onLocationUpdate(location: Location)
    }


}







