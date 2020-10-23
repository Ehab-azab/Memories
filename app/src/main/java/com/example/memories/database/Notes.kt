package com.example.memories.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val description : String?=null ,
    @ColumnInfo
    val date : String?=null,
    @ColumnInfo
    val long :Int? = null ,
    @ColumnInfo
    val pathImage : String?=null

)