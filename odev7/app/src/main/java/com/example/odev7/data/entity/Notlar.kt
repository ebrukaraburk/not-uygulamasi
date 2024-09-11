package com.example.odev7.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "toDos")
data class Notlar(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "id") @NotNull var id:Int,
                   @ColumnInfo(name = "title") @NotNull var title:String,
                   @ColumnInfo(name = "context") @NotNull var context:String,
                  @ColumnInfo(name = "date") @NotNull var date:String
    ){
}