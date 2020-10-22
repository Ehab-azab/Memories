package com.example.memories.base

import android.R
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.reflect.Array
import java.security.Permission
import java.security.Permissions


// 
//this for all requests 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class baseRquest {
    fun IsPermitionGRANTED(context: Context, PermitionName: Permission): Boolean {

        return (ContextCompat.checkSelfPermission(
            context,
            PermitionName.toString()
        ) == PackageManager.PERMISSION_GRANTED)
    }

    fun requistPermitionFromUser(
        context: Context,
        chekPermitionName: Permission,
        txtToShowInMessage: String,
        arrayOfRequests: String,
        PermitionRequistCode: Int
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                chekPermitionName.toString()
            )
        ) {
            //thats meen that u shoud explain why u want this permition
            var message = base()
            message.showDialog(message = txtToShowInMessage,
                posActionName = "OK", posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    //add Requist
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(arrayOfRequests), PermitionRequistCode
                    )
                }, negActionName = "cancel",
                negAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })

        } else {
            //get requist
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(arrayOfRequests), PermitionRequistCode
            )
        }
    }
}