package com.example.fakestore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.databinding.ItemStoreBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val productos: Array<JSONObject>): RecyclerView.Adapter<MainAdapter.MainHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.render(productos[position])
    }
    override fun getItemCount(): Int = productos.size
    class MainHolder(val binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root){
        fun render(producto: JSONObject){
            binding.TVNombreProducto.setText(producto.getString("title"))
            binding.TVPreciProducto.setText(producto.getString("price"))
        }
    }
}