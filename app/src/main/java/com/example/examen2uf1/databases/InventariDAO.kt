package com.example.examen2uf1.databases

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.examen2uf1.model.Moble

@Dao
interface InventariDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMoble(moble: Moble)

    @Query("SELECT * FROM mobles ")
    fun getMobles(): LiveData<List<Moble>>

    @Update
    fun updateMoble(moble: Moble)

    @Delete
    fun deleteMoble(moble: Moble)

}