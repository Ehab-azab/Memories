package com.example.memories.base

import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.text.BoringLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.security.Permission


// 
//this for base methods 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class base : AppCompatActivity() {
    var dialog: AlertDialog? = null
    fun showDialog(
        title: String? = null,
        message: String,
        posActionName: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionName: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true
    ) {
        dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posActionName, posAction)
            .setNegativeButton(negActionName, negAction)
            .setCancelable(cancelable)
            .show()

    }

    fun hidedialog() {
        dialog?.dismiss()
    }

    fun checkPermition(context: Context, PermitionName: Permission): Boolean {
        return baseRquest().IsPermitionGRANTED(context, PermitionName)
    }

    fun takeRequest(
        context: Context,
        chekPermitionName: Permission,
        txtToShowInMessage: String,
        arrayOfRequests: String,
        PermitionRequistCode: Int
    ) {
        baseRquest().requistPermitionFromUser(
            context,
            chekPermitionName,
            txtToShowInMessage,
            arrayOfRequests,
            PermitionRequistCode
        )
    }

    fun onreq(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val number: Int = 0
            if (requestCode == number) {
                //ToDo after take request
            }
        }
    }
}