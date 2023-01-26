package com.example.examen2uf1.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen2uf1.R
import com.example.examen2uf1.databases.InventariDatabase
import com.example.examen2uf1.databinding.FragmentLlistaBinding
import com.example.examen2uf1.listAdapter.MobleRVAdapter
import com.example.examen2uf1.listAdapter.RecyclerClickListener
import com.example.examen2uf1.model.Moble
import com.example.examen2uf1.viewmodel.InventariViewModel
import kotlinx.coroutines.launch


class LlistaFragment : Fragment() {
    private lateinit var binding: FragmentLlistaBinding
    private lateinit var adapter: MobleRVAdapter
    lateinit var inventariViewModel: InventariViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate<FragmentLlistaBinding>(inflater, R.layout.fragment_llista,container,false);
        inventariViewModel = ViewModelProvider(this).get(InventariViewModel::class.java)
        binding.buttonToAdd.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_llistaFragment_to_afegirFragment)
        }
        setRecyclerView()
        observeMoble()

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun setRecyclerView() {

        val mobleRecyclerview = binding.recyclerView
        mobleRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        mobleRecyclerview.setHasFixedSize(true)
        adapter = MobleRVAdapter()
        adapter.setItemListener(object : RecyclerClickListener {

            override fun onItemClick(position: Int) {

                val mobleList = adapter.currentList.toMutableList()
                Toast.makeText(requireContext(),"${mobleList[position].nom} , ${mobleList[position].quantitat}, ${mobleList[position].preu}, ${mobleList[position].stock} " , Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(R.id.action_llistaFragment_to_modifyFragment)
            }

            override fun onItemRemoveClick(position: Int) {
                val mobleList = adapter.currentList.toMutableList()
                var nom = mobleList[position].nom
                var quantitat = mobleList[position].quantitat
                var preu = mobleList[position].preu
                var stock = mobleList[position].stock
                val removeMoble = Moble(nom, quantitat, preu, stock)
                mobleList.removeAt(position)
                adapter.submitList(mobleList)
                lifecycleScope.launch {
                    inventariViewModel.eliminarMoble(requireContext(),removeMoble)
                    Toast.makeText(requireContext(),"Mueble Eliminado" , Toast.LENGTH_SHORT).show()

                }
            }
        })
        mobleRecyclerview.adapter = adapter
    }

    private fun observeMoble() {
        inventariViewModel.obtenirMobles(requireContext())!!.observe(viewLifecycleOwner, Observer { llistaMoble ->
            adapter.submitList(llistaMoble)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}