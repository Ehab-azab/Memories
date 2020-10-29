package com.example.memories.images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File


// 
//this class for converting path to bitmab 
// Created by Ehab Azab on 10/28/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
open class images(var ay_HAga: String) {
    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return imagebitmab
    }
}