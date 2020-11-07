package com.example.memories.Time

import java.text.SimpleDateFormat
import java.util.*


// 
//this class for time 
// Created by Ehab Azab on 11/7/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class Time {
    //time
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    fun timeconverter(time: Long?) {
        SimpleDateFormat(" dd MM yyyy  hh:mm:ss ")
            .format(time)
    }
}