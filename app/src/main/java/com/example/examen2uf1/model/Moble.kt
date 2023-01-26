package com.example.examen2uf1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "mobles")
data class Moble (
    @ColumnInfo(name = "name")
    var nom : String,
    @ColumnInfo(name = "quantity")
    var quantitat : Int,
    @ColumnInfo(name = "price")
    var preu : Int,
    @ColumnInfo(name = "stock")
    var stock : String
){
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}