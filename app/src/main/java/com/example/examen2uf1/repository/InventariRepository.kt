package com.example.examen2uf1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.examen2uf1.databases.InventariDatabase
import com.example.examen2uf1.model.Moble
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventariRepository {

    companion object {

        var inventariDatabase: InventariDatabase? = null

        fun initializeDB(context: Context): InventariDatabase {
            return InventariDatabase.getDatabase(context)
        }

        //INSERT Moble
        fun insertMoble(context: Context, moble:Moble) {

            inventariDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                inventariDatabase!!.inventariDAO().addMoble(moble)
            }
        }

        //QUERY Moble
        fun getMobles(context: Context): LiveData<List<Moble>> {
            inventariDatabase = initializeDB(context)
            return inventariDatabase!!.inventariDAO().getMobles()
        }

        //MODIFY Moble
        fun modifyMobles(context: Context, moble: Moble){
            inventariDatabase = initializeDB(context)
            return inventariDatabase!!.inventariDAO().updateMoble(moble)
        }

        //DELETE Moble
        fun deleteMobles(context: Context,moble: Moble) {
            inventariDatabase = initializeDB(context)
//            return inventariDatabase!!.inventariDAO().deleteMoble(moble)

        }





    }


}