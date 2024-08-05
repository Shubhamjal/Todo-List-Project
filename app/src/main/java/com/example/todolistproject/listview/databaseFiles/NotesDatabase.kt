package com.example.todolistproject.listview.databaseFiles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 1, exportSchema = true)
abstract class NotesDatabase :RoomDatabase(){
    companion object{
        private var notesDatabase:NotesDatabase?=null

        fun getInstance(context:Context):NotesDatabase{
            if (notesDatabase==null){
                notesDatabase=Room.databaseBuilder(context,NotesDatabase::class.java,"NotesDatabase").allowMainThreadQueries().build()
            }
            return notesDatabase!!
        }
    }

}
