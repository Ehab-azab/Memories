package com.example.memories.database

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun addNote(note : Note)

    @Delete
    fun deleteNote(note : Note)


    @Update
    fun updataNote(note : Note)


    @Query("Delete from Note where id = :id")
    fun deletebyidNote(id : Int)


    @Insert
    fun getAllNotes(data :List<Note>)


    @Query("select * from Note where title like :word and description like :word")
    fun search(word :String)

}