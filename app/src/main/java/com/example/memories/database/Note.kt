package com.example.memories.database

import androidx.room.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)

    var id: Int? = null,
    @ColumnInfo
    var title: String? = null,
    @ColumnInfo
    var day: String? = null,
    @ColumnInfo
    var month: String? = null,
    @ColumnInfo
    var year: String? = null,
    @ColumnInfo
    var description: String? = null,
    @ColumnInfo
    var date: String? = null,
    @ColumnInfo
    var long: Float? = null,
    /*@ColumnInfo
    var pathImage : List<String>?=null,*/

    @ColumnInfo
    var lat: Float? = null,
    @ColumnInfo
    var startPoint: Float? = null,
    @ColumnInfo
    var endPoint: Float? = null














)