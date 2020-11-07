package com.example.memories.Requists

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.memories.R
import com.example.memories.messages.messages


// 
//This for location requist 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class Requests() {
    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun getPermission(
        context: Context,
        permission: String,
        Permission_Request_Code: Int,
        messageifPermissionWant: String
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
            messages().showDialog(context = context,
                message = messageifPermissionWant,
                positiveBtnName = "OK",
                positiveBtnAction = DialogInterface.OnClickListener { dialog, which ->
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf(permission),
                        Permission_Request_Code
                    )
                },
                negativeBtnName = "Cancle",
                negativeBtnAction = DialogInterface.OnClickListener { dialog, which ->
                    messages().hidedialog()
                })
        } else {
            ActivityCompat.requestPermissions(context, arrayOf(permission), Permission_Request_Code)
        }
    }

    fun mangeResponse(
        requestCode: Int, MyrequestCode: Int, permissions: Array<out String>,
        grantResults: IntArray, context: Context, packageName: String
    ): Boolean {
        if (requestCode == MyrequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                return true
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        permissions[0]
                    )
                ) {
                    messages().showDialog(context = context,
                        message = "Thas app May be Not work Plese Accept request",
                        positiveBtnName = "OK",
                        positiveBtnAction = DialogInterface.OnClickListener { dialog, which ->
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts(
                                    context.getString(R.string.package_name),
                                    packageName,
                                    null
                                )
                            )
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivityForResult(
                                intent,
                                MyrequestCode
                            )
                        },
                        negativeBtnName = "Cancle",
                        negativeBtnAction = DialogInterface.OnClickListener { dialog, which ->
                            messages().hidedialog()
                        })
                }
            }

        }
        return false
    }


}