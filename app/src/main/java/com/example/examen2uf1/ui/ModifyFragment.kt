package com.example.examen2uf1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.examen2uf1.R
import com.example.examen2uf1.databinding.FragmentLlistaBinding
import com.example.examen2uf1.databinding.FragmentModifyBinding
import com.example.examen2uf1.listAdapter.MobleRVAdapter
import com.example.examen2uf1.viewmodel.InventariViewModel

class ModifyFragment : Fragment() {
    private lateinit var binding : FragmentModifyBinding
    lateinit var inventariViewModel: InventariViewModel
    lateinit var nom: String
    lateinit var quantityModify: String
    lateinit var preuModify: String
    lateinit var stockModify: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentModifyBinding>(inflater, R.layout.fragment_modify,container,false);
        inventariViewModel = ViewModelProvider(this).get(InventariViewModel::class.java)

        nom = binding.editTextModifyNom.toString()

        quantityModify = binding.editTextModifyQuantity.toString()
        preuModify = binding.editTextModifyPreu.toString()
        stockModify = binding.editTextModifyStock.toString()

        inventariViewModel.obtenirMobles(requireContext())?.observe(viewLifecycleOwner, Observer { mobleLlistat ->
            nom = { mobleLlistat[0].nom }.toString()
            quantityModify = {mobleLlistat[0].quantitat}.toString()
            preuModify = {mobleLlistat[0].preu}.toString()
            stockModify = {mobleLlistat[0].stock}.toString()
            Log.i("prova","$nom")
        })

        setHasOptionsMenu(true)
        return binding.root
    }

}