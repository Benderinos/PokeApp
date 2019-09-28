package com.david.pokeapp.ui.home

import androidx.core.view.ViewCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.david.domain.model.Pokemon
import com.david.pokeapp.BR
import com.david.pokeapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonViewHolder(private var binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon, viewModel: HomeViewModel) {
        binding.setVariable(BR.pokemon, pokemon)
        binding.setVariable(BR.homeViewModel, viewModel)
        ViewCompat.setTransitionName(binding.root.ivPokemonPhoto, pokemon.number.toString())
        binding.executePendingBindings()
    }
}