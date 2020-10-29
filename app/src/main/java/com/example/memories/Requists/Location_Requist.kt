package com.example.memories.Requists

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.memories.base.base
import java.util.jar.Manifest


// 
//This for location requist 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class Location_Requist(var context: Context) {

    fun IsLocationGRANTED(): Boolean {
        return (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    fun requistLocationPermitionFromUser(PermitionRequistCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            //thats meen that u shoud explain why u want this permition
            var message = base()
            message.showDialog(message = "Application Wants to Acces your Location To save the Location Of Memory",
                posActionName = "OK", posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    //add Requist
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        PermitionRequistCode
                    )
                }, negActionName = "cancel",
                negAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })

        } else {
            //get requist
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PermitionRequistCode
            )
        }
    }


}