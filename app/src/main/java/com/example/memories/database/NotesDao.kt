package com.example.memories.database

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun addNote(note : Note)

    @Delete
    fun deleteNote(note : Note)


    @Update
    fun updateNote(note : Note)


    @Query("Delete from Note where id = :id")
    fun deleteByIdNote(id : Int)


    @Query("select * from Note")
    fun getAllNotes():List<Note>


    @Insert
    fun insertAllNotes(data :List<Note>)


    @Query("select * from Note where title like :word or description like :word")
    fun search(word :String):List<Note>

    @Query("SELECT * FROM Note WHERE lat or long BETWEEN :startPoint AND :endPoint")
    fun searchArea(startPoint :Float,endPoint :Float)

}