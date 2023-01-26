package com.example.examen2uf1.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.examen2uf1.model.Moble
import com.example.examen2uf1.repository.InventariRepository

class InventariViewModel : ViewModel() {
    var moble: LiveData<List<Moble>>? = null


    fun newMoble(context: Context, nom:String, quantitat: Int, preu : Int, stock: String ){
        var mobleVM=Moble(nom, quantitat, preu, stock)
        InventariRepository.insertMoble(context,mobleVM)
    }

    fun obtenirMobles(context: Context): LiveData<List<Moble>>?{
        moble = InventariRepository.getMobles(context)
        return moble
    }

    fun eliminarMoble(context: Context,moble: Moble){
        var mobles = InventariRepository.deleteMobles(context, moble)
        return mobles
    }

    fun modificarMoble(context: Context, mobles: Moble){
        var mobles = InventariRepository.modifyMobles(context, mobles)
        return mobles
    }


}