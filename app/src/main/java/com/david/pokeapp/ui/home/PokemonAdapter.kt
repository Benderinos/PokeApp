package com.david.pokeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.david.domain.model.Pokemon
import com.david.pokeapp.R
import com.david.pokeapp.viewmodel.HomeViewModel

class PokemonAdapter(private val data: List<Pokemon>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun getItemViewType(position: Int): Int = R.layout.pokemon_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(data[position], viewModel)
    }
}