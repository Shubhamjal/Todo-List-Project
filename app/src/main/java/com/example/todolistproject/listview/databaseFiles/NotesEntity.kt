package com.example.todolistproject.listview.databaseFiles

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=0,
    var title:String?="",
    var description:String?="",

)
