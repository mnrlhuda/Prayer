package com.example.prayer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prayer.DetailActivity
import com.example.prayer.databinding.ItemDoaBinding
import com.example.prayer.model.Doa

class DoaAdapter(private var list: List<Doa>) : RecyclerView.Adapter<DoaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDoaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doa = list[position]
        holder.binding.tvJudul.text = doa.doa
        holder.binding.root.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("doa", doa)
            }
            context.startActivity(intent)
        }
    }

    fun updateList(newList: List<Doa>) {
        list = newList
        notifyDataSetChanged()
    }
}