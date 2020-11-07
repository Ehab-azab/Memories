package com.example.memories.images

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File


// 
//this class for converting path to bitmab 
// Created by Ehab Azab on 10/28/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
open class images : AppCompatActivity() {


    @SuppressLint("LongLogTag")
    fun takeImagePathFromGalary(data: Intent?): String {
        val selectedImage: Uri = data?.getData()!!
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null)
        cursor!!.moveToFirst()
        val columnIndex = cursor!!.getColumnIndex(filePathColumn[0])
        val picturePath = cursor!!.getString(columnIndex)
        cursor!!.close()
        Log.e("takeImagePathFromGalary fun", selectedImage.toString())
        return@takeImagePathFromGalary picturePath
    }

    fun activeTakePhoto(CAMERA_AND_GALARY_REQUEST_CODEvar: Int) {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_AND_GALARY_REQUEST_CODEvar)
        }
    }

    fun activeGallery(context: Context, RESULT_LOAD_IMAGE: Int) {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        Log.e("galary sec 1", "anagwa active galary")
        startActivityForResult(intent, RESULT_LOAD_IMAGE)

    }

    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return@PathToBitmab imagebitmab
    }

}