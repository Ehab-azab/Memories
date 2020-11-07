package com.example.memories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 5, exportSchema = false)
@TypeConverters(converter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao ():NoteDao

    companion object{
        fun getInstance(con : Context):NotesDatabase {
            val notesdata = Room.databaseBuilder(con, NotesDatabase::class.java, "notes-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return notesdata
        }
    }

}