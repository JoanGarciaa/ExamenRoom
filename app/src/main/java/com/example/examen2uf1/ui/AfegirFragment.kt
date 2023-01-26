package com.example.examen2uf1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.examen2uf1.R
import com.example.examen2uf1.databinding.FragmentAfegirBinding
import com.example.examen2uf1.databinding.FragmentLlistaBinding
import com.example.examen2uf1.viewmodel.InventariViewModel

class AfegirFragment : Fragment() {
    private lateinit var binding : FragmentAfegirBinding
    private lateinit var viewModel: InventariViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentAfegirBinding>(inflater, R.layout.fragment_afegir,container,false);
        viewModel = ViewModelProvider(this)[InventariViewModel::class.java]


        binding.buttonAddMoble.setOnClickListener {
            var nom = binding.editTextAfegirNom.text.toString()
            var quantitat = binding.editTextAfegirQuantitat.text.toString().toInt()
            var preu = binding.editTextAfegirPreu.text.toString().toInt()
            var stock = binding.editTextAfegirStock.text.toString()
            if(nom.isEmpty() && stock.isEmpty()){
                Toast.makeText(requireContext(), "Emplena tots els camps siusplau", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.newMoble(requireContext(),nom, quantitat, preu, stock)
                Toast.makeText(requireContext(), "S'ha inserit correctament", Toast.LENGTH_SHORT).show()
            }


        }

        setHasOptionsMenu(true)
        return binding.root
    }

}