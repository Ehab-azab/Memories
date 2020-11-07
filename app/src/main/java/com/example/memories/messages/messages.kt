package com.example.memories.messages

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


// 
//this for base methods 
// Created by Ehab Azab on 10/22/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
open class messages {
    var dialog: AlertDialog? = null
    fun showDialog(
        context: Context,
        title: String? = null,
        message: String? = null,
        positiveBtnName: String? = null,
        positiveBtnAction: DialogInterface.OnClickListener? = null,
        negativeBtnName: String? = null,
        negativeBtnAction: DialogInterface.OnClickListener? = null
    ) {
        dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveBtnName, positiveBtnAction)
            .setNegativeButton(negativeBtnName, negativeBtnAction)
            .setCancelable(false)
            .show()
    }

    fun hidedialog() {
        dialog?.dismiss()
    }


}