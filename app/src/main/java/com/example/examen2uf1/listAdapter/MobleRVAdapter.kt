package com.example.examen2uf1.listAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2uf1.R
import com.example.examen2uf1.model.Moble

class MobleRVAdapter : ListAdapter<Moble, MobleRVAdapter.MobleHolder>(DiffCallback()) {

    class MobleHolder(view:View): RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobleHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        val mobleHolder = MobleHolder(v)


        val moble = mobleHolder.itemView.findViewById<CardView>(R.id.card_view)
        moble.setOnClickListener {
            listener.onItemClick(mobleHolder.adapterPosition)
        }

        val mobleDelete = mobleHolder.itemView.findViewById<TextView>(R.id.muebleDelete)
        mobleDelete.setOnClickListener {
            listener.onItemRemoveClick(mobleHolder.adapterPosition)
        }

        return mobleHolder
    }

    override fun onBindViewHolder(holder: MobleHolder, position: Int) {
        val currentItem = getItem(position)
        val nombre = holder.itemView.findViewById<TextView>(R.id.item_nombre)
        nombre.text = currentItem.nom

        val precio = holder.itemView.findViewById<TextView>(R.id.item_precio)
        precio.text = currentItem.preu.toString()
    }

    class DiffCallback : DiffUtil.ItemCallback<Moble>() {
        override fun areItemsTheSame(oldItem: Moble, newItem: Moble) =
            oldItem.nom == newItem.nom

        override fun areContentsTheSame(oldItem: Moble, newItem: Moble) =
            oldItem == newItem
    }
}